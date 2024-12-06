from pybit.unified_trading import HTTP
import os
from dotenv import load_dotenv
import time
from datetime import datetime
import logging
import json
from utils import create_order, create_trade, check_health

logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

load_dotenv()
api_key = os.getenv('BYBIT_DEMO_API_KEY')
api_secret = os.getenv('BYBIT_DEMO_SECRET')

session = HTTP(
    #testnet=True,
    demo=True,
    api_key=api_key,
    api_secret=api_secret,
    log_requests=True
)


# Trading parameters
SYMBOL = "DOGEUSDT"  # DOGE is volatile and has low price
CLOSE_TIMEOUT = 300  # 5 minute in seconds
LEVERAGE = 1
QTY=12

def get_account_balance():
    """Get current account balance in USDT"""
    balance = session.get_wallet_balance(accountType="UNIFIED", coin="USDT")
    return float(balance['result']['list'][0]['totalEquity'])

def set_leverage(leverage: int, symbol: str) -> bool:
    """Set leverage if changed"""    
    session.set_leverage(
            category="linear",
            symbol=symbol,
            buyLeverage=str(leverage),
            sellLeverage=str(leverage)
        )

    return True
    
def position_still_open(symbol: str) -> bool:
    try:
        response = session.get_positions(
            category="linear",
            symbol=symbol
        )
        positions = response['result']['list'] 
        open_positions = [position for position in positions if float(position['size']) > 0]
        if len(open_positions) > 0:
            return True
        else:
            return False
    except Exception as e:
        print(f"Error getting positions: {str(e)}")
        return True   

def execute_trade(action: str, quantity: float) -> float:
    """
    Execute a trade with the specified parameters
    
    Args:
        action (str): 'long' or 'short'
        quantity (float): Amount to trade
        leverage (float): Leverage to use
    
    Returns:
        float: Profit/loss from the trade
    """
    try:
        # Set leverage // TODOO
        current_balance = get_account_balance()

        logger.info("Trade side is: " +  action.upper())
        opening_balance = get_account_balance()
        logger.info(f"Opening balance: {opening_balance:.2f} USDT")
        
        
        ticker = session.get_tickers(
            category="linear",
            symbol=SYMBOL
        )
        current_price = float(ticker['result']['list'][0]['lastPrice'])

        sl_percentage = 1
        sl_price = current_price * (1 + sl_percentage/100) if action == "short" else current_price * (1 - sl_percentage/100) 
        logger.info(f"Current price: {current_price} and stop loss set at: {sl_price} for {action}")

        side = "Buy" if action == "long" else "Sell"
        session.place_order(
            category="linear",
            symbol=SYMBOL,
            side=side,
            orderType="Market",
            qty=quantity,
            stopLoss=str(sl_price),
            positionIdx=0
        )

        response = create_order(SYMBOL, side.upper(), current_balance, QTY, LEVERAGE)
        response_json = json.loads(response.text)
        open_order_id = response_json['id']

    
        time.sleep(CLOSE_TIMEOUT)

        if(not position_still_open(SYMBOL)):
            return -1 if action == "long" else 1

        close_side = "Sell" if action == "long" else "Buy"
        session.place_order(
            category="linear",
            symbol=SYMBOL,
            side=close_side,
            orderType="Market",
            qty=quantity,
            positionIdx=0,
            reduceOnly=True
        )
        
        

        time.sleep(10)
        closing_balance = get_account_balance()

        response = create_order(SYMBOL, close_side.upper(), closing_balance, QTY, LEVERAGE)
        response_json = json.loads(response.text)
        close_order_id = response_json['id']

        create_trade(open_order_id, close_order_id)


        profit_loss = closing_balance - opening_balance
        
        # logger.info(f"Closed position at {closing_price}")
        logger.info(f"Closing balance: {closing_balance:.2f} USDT")
        logger.info(f"Profit/Loss: {profit_loss:.2f} USDT")
        
        return profit_loss
        
    except Exception as e:
        logger.error(f"Error executing trade: {str(e)}")
        return 0.0

if __name__ == "__main__":
    health = check_health()

    while(health is False):
        print("Waiting for 10 seconds for system to be healthy...")
        time.sleep(10)
        health = check_health()

    

    starting_balance = get_account_balance()
    mid_balance = starting_balance
    print("########################")
    print(f"Starting with balance: {starting_balance:.2f} ")
    print("########################")

    action = "long"

    while(True):
        pnl = execute_trade(action, QTY)  
        other_side = "long" if action == "short" else "short"
        action = action if pnl > 0 else other_side
        




    
    

    
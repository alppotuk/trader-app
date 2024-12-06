import requests

url = "http://springboot:8080/api/v1"

def create_order(symbol, side, balance_amount, quantity, leverage):
    data = {
        "symbol": symbol,
        "side": side,
        "balanceAmount": balance_amount,
        "quantity": quantity,
        "leverage": leverage
    }

    headers = {'Content-Type': 'application/json'}

    response = requests.post(url + "/orders/create", json=data, headers=headers)
    return response

def create_trade(opening_order_id, closing_order_id):
    data = {
        "openingOrderId": opening_order_id,
        "closingOrderId": closing_order_id
    }

    headers = {'Content-Type': 'application/json'}

    response = requests.post(url + "/trades/create", json=data, headers=headers)
    return response

def check_health() -> bool:
    try:
        response = requests.get(url + "/status")
        return response.status_code == 200
    except requests.exceptions.RequestException as e:
        print(f"Error making request: {e}")
        return False
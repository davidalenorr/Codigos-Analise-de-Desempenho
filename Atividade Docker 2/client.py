import requests
import time

url = "http://localhost:8080"

print(f"test {url} ---")

for i in range(10): 
    try:
        response = requests.get(url)
        print(f"requisition {i+1}: {response.text.strip()}")
    except requests.exceptions.ConnectionError:
        print("error: unable to connect to the server.")

    time.sleep(0.5) 
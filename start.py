import requests
from flask import Flask
from flask import request
app = Flask(__name__)


def ledFunction(params):
	print("led function "+ params[0])
	if(params[0] == 'on'):
		return requests.get('http://192.168.43.37:80/LED=ON').text
	if(params[0] == 'off'):
		return requests.get('http://192.168.43.37:80/LED=OFF').text


def handleAction(action, params):
	if(action=="led"):
		return ledFunction(params)


@app.route("/executetest", methods=['POST'])
def executePostTest():
	data = request.json
	return "data";

@app.route("/execute", methods=['POST'])
def executePost():
	data = request.json
	print (data)
	return handleAction(data['action'], data['params'])


if __name__ == "__main__":
    app.run(host= '0.0.0.0')
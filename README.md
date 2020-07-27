# cms
Paypal cms assignment

curl for adding the data 
curl -X POST \
  http://localhost:8888/sku/save \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: fb2a7610-665d-4b4d-9cb6-f316923c9025' \
  -H 'cache-control: no-cache' \
  -d '{
	"title": "Nikee shoes",
	"brandName": "shoes",
	"skuMetaData" : {
		"description": "I love Nike"
	}
}'

curl for  the search

curl -X GET \
  'http://localhost:8888/sku/search/?keyword=Nike' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 5b61ee89-5a59-4771-9b5a-417515bf8981' \
  -H 'cache-control: no-cache'
  
 curl for  the deleting an SKU by id
 
 
 curl -X DELETE \
  'http://localhost:8888/sku/delete/?skuId=4' \
  -H 'Postman-Token: 12b15a69-b8e4-4210-b037-96f5f872d4de' \
  -H 'cache-control: no-cache'
  
update
curl -X PUT \
  'http://localhost:8888/sku/update/?skuId=1' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: fc1ab43c-44b9-453f-855c-ee4218babe49' \
  -H 'cache-control: no-cache' \
  -d '{
	"title": "Nikeee shoess",
	"brandName": "shoes",
	"skuMetaData" : {
		"description": "I love Adidas"
	}
}'

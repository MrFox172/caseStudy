from random_address import real_random_address
import names, random

f = open("productdata.sql","w")

productNameEnders = [" machine", " gizmo", " thingy", " thingamabob", " tool", " food", " product"]

#Hard coded status inserts
productsInsert = 'insert into products("description_details", "description_main", "product_name", "picture_url", "price") values ({description_details},{description_main},{product_name},{picture_url},{price:.2f});'


i = 0
while i<50:
   output = productsInsert
   fName = names.get_first_name()
   currentProduct = fName+random.choice(productNameEnders)
   description_details = "'This is certainly a "+currentProduct+"'"
   description_main = "'This product is a game changer! You will definitely need the "+currentProduct+"'"
   picture_url = "'placeholder'"
   currentProduct = "'"+currentProduct+"'"
   price = random.random()*100
   output = output.format(description_details=description_details, description_main=description_main, product_name=currentProduct,picture_url=picture_url,price=price)
   f.write(output+"\n")
   i+=1

f.close()
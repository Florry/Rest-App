package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;

import models.Product;
import play.libs.Json;
import play.mvc.*;

public class Products extends Controller
{
	
	public static Result allProducts()
	{
		List<Product> products = new ArrayList<Product>();
		products = Ebean.find(Product.class).findList();
		return ok(Json.toJson(products));
	}
	
	public static Result oneProduct(int id)
	{
		Product product = Ebean.find(Product.class, id);
		return ok(Json.toJson(product));
	}
	
	public static Result createProduct()
	{
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		Product product = new Product();
		product.name = form.get("name")[0];
		product.description = form.get("description")[0];
		product.cost = Integer.parseInt(form.get("cost")[0]);
		
		Ebean.save(product);
		return ok();
	}
	
	public static Result updateProduct(int id)
	{
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		Product product = Ebean.find(Product.class, id);
		product.name = form.get("name")[0];
		product.description = form.get("description")[0];
		product.cost = Integer.parseInt(form.get("cost")[0]);
		
		Ebean.update(product);
		return ok();
	}
	
	public static Result deleteProduct(int id)
	{
		Product product = Ebean.find(Product.class, id);
		Ebean.delete(product);
		return ok();
	}
}

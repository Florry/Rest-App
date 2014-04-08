package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;

import models.Product;
import play.data.Form;
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
		Product product = Form.form(Product.class).bindFromRequest().get();
		Ebean.save(product);
		return ok();
	}
	
	public static Result updateProduct(int id)
	{
		Product product = Form.form(Product.class).bindFromRequest().get();
		product.id = id;
		
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

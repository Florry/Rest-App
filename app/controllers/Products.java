package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Product;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.avaje.ebean.Ebean;

public class Products extends Controller
{
	
	public static Result allProducts()
	{
		List<Product> products = new ArrayList<Product>();
		products = Ebean.find(Product.class).findList();
		return ok(Json.toJson(products));
	}
	
	public static Result createProduct()
	{
		final Form<Product> form = Form.form(Product.class).bindFromRequest();
		final Map<String, String[]> formData = request().body().asFormUrlEncoded();
		
		if (!form.hasErrors())
		{
			final Product product = Form.form(Product.class).bindFromRequest().get();
			Ebean.save(product);
		} else
		{
			for (final String key : form.errors().keySet())
			{
				flash().put(
						key + "-error",
						key
								+ " : "
								+ form.errorsAsJson().findValue(key).get(0).asText()
										.replace('[', ' ').replace(']', ' '));
			}
			flash().put("name", formData.get("name")[0]);
			flash().put("description", formData.get("description")[0]);
			flash().put("cost", formData.get("cost")[0]);
		}
		return redirect(routes.Products.index());
	}
	
	public static Result deleteProduct(int id)
	{
		final Product product = Ebean.find(Product.class, id);
		Ebean.delete(product);
		return redirect(routes.Products.index());
	}
	
	public static Result index()
	{
		List<Product> products = new ArrayList<Product>();
		products = Ebean.find(Product.class).findList();
		return ok(index.render(products));
	}
	
	public static Result oneProduct(int id)
	{
		final Product product = Ebean.find(Product.class, id);
		return ok(Json.toJson(product));
	}
	
	public static Result updateProduct(int id)
	{
		final Product product = Form.form(Product.class).bindFromRequest().get();
		product.id = id;
		
		Ebean.update(product);
		return ok();
	}
}

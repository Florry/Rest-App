package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

@Entity
public class Product
{
	@Required
	@Min(1)
	public int cost;
	@Required
	@MinLength(3)
	@MaxLength(32)
	public String description;
	@Id
	@GeneratedValue
	public int id;
	@Required
	@MinLength(2)
	public String name;
	
	public Product()
	{}
	
	public int getCost()
	{
		return this.cost;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
}

package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product
{
	@Id
	@GeneratedValue
	public int id;
	public String name;
	public String description;
	public int cost;
	
	public Product()
	{}
}

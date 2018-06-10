package test;
public class Dish
{
	private String name;
	private double cost;
	private String detail;
	private int index;
	public Dish(String n, double c, String d,int i)
	{
		name = n;
		cost = c;
		detail = d;
		index = i;
	}
	public Dish() {}
	public String getName()
	{
		return name;
	}
	public double getCost()
	{
		return cost;
	}
	public String getDetail()
	{
		return detail;
	}
	public String toString()
	{
		return name + " ¼Û¸ñ:" + cost + "\n" + detail;
	}
	public int getNmuber()
	{
		return index;
	}
	public void setName(String n)
	{
		name = n;
	}
	public void setCost(double c)
	{
		cost = c;
	}
	public void setIndex(int i)
	{
		index = i;
	}
	public void setDetail(String s)
	{
		detail = s;
	}
}


class prime
{
	public static void main(String arg[])
	{
	if(arg.length==1)
		{
		int n=Integer.parseInt(arg[0]);
		String pm="";
		
		for(int i=0; i<=n; i++)
		{
			int c=0;
			for(int num=i; num>=1; num--)
			{
			if(i%num==0)
			{
			c=c+1;	
			}
		}
			if(c==2)
			{
			pm=pm+i+"";
			
			}
		}
		System.out.println("prime nos= "+pm);						
		
		}
	else
	{
	System.out.println("Wrong input");
		
	}

	}
}


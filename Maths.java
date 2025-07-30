import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import java.lang.String;
import java.util.Scanner;
class Convert_To_Function
{
	double coeff[]=new double[10];
	int pow[]=new int[10];
	char variable;
	int top=-1;
	double fx,constant;
	String func;
	Convert_To_Function(String func,char v)
	{
		this.func="+"+func;
		variable=v;
		wordExtractor();
	}
	void push(double c,int p)
	{
		top++;
		if(top==10)
		{
			System.out.println("Max degree of Polynomial function allowed is 10");
			System.exit(0);
		}
		//System.out.println(c);
		coeff[top]=c;
		pow[top]=p;
	}
	//double pop()
	//{
	//	if(top==-1)//May not be required
	//	{
	//		System.out.println("Constant function");
	//		return 0.0;
	//	}
	//	return coeff[top--];
	//}
	void functionUpdater(String word,char ch)
	{
		if(word=="")
			return;
		word=Reverse(word);
		double x;
		int y=1,t;
		int i=word.indexOf(variable);
			if(i!=-1)
			{
				x=Double.parseDouble(word.substring(0,i));
				if(ch=='-')
					x*=-1.0;
				t=word.indexOf('^');
				if(t!=-1)
				y=Integer.parseInt(word.substring(t+1));
				push(x,y);
			}
			else
			{
				constant=Double.parseDouble(word);
				if(ch=='-')
					constant*=-1;
			}
	}
	String Reverse(String word)
	{
        return new StringBuilder(word).reverse().toString();
    }
	void wordExtractor()
	{
		String word="";
		char ch;
		for(int i=func.length()-1;i>=0;i--)
		{
			ch=func.charAt(i);
			if(ch=='+'||ch=='-')
			{
				functionUpdater(word,ch);
				word="";
			}
			else
				word+=func.charAt(i);
		}
	}
	double Calculator(double x)
	{
		fx=constant;
		for(int i=top;i>=0;i--)
			fx+=coeff[i]*Math.pow(x,pow[i]);
		return fx;
	}
}
class Main
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the variable name: ");
		char v=sc.next().charAt(0);
		System.out.println("Enter the Equation in constant*[variable]^power+next term format");
		String func=sc.next();
		Convert_To_Function Fx=new Convert_To_Function(func,v);
		//Fx.wordExtractor();
		System.out.println("Enter the value of the variable: ");
		double x=sc.nextDouble();
		System.out.println("The output is: "+Fx.Calculator(x));
	}
}

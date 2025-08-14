import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import java.lang.String;
import java.util.Scanner;
class Convert_To_Function
{
	String pre_defined_func[]={"sin","cosec","tan","sec","cos","cot"};
	double coeff[]=new double[10];
	int pow[]=new int[10];
	float pre_def[]=new float[10];
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
	void push(double c,int p,float index_of_pre_def)
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
		pre_def[top]=index_of_pre_def;
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
		word=new StringBuilder(word).reverse().toString();
		double x;
		//System.out.println(word);
		int y=1,t,t1=-1,i,ind_x,t2=-1;
		float ind=-1.0f;
		for(i=0;i<6;i++)
		{
			//System.out.println("Checking char"+pre_defined_func[i]);
			t1=word.lastIndexOf(pre_defined_func[i]);//.charAt(0)
			//System.out.println("sin index"+t1);
			if(t1!=-1)
			{
				//if(i==4)
				//	t1+=4;
				//else
				//	t1+=2;
				break;
			}
		}//corrected loop 
		t=word.indexOf('^');
		t2=t1;
			if(t1!=-1)
			{
				t1=word.lastIndexOf('^');
				ind_x=word.lastIndexOf(variable);
				//System.out.println("t1 + ind_x: "+t1+" "+ind_x +" "+store_i);
				if(t1!=-1)
					ind=i+(float)0.1f*Integer.parseInt(word.substring(t1+1,ind_x));
				else
					ind=i+0.1f;
				//System.out.println("ind: "+ind);
			}
			ind_x=word.indexOf(variable);
			//System.out.println("ind_x: "+ind_x);
			if(ind_x!=-1)
			{
				x=Double.parseDouble(word.substring(0,ind_x));
				if(ch=='-')
					x*=-1.0;
				//t=word.indexOf('^');
				//System.out.println("t: "+t);
				if(t!=-1 && t2!=-1)
					y=Integer.parseInt(word.substring(t+1,t2));//
				else
					y=Integer.parseInt(word.substring(t+1));
					push(x,y,ind);
			}
			else
			{
				constant=Double.parseDouble(word);
				if(ch=='-')
					constant*=-1;
			}
	}
	//String Reverse(String word)
	//{
    //    return ;
    //}
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
		//String words[]=func.trim().split("[+-]+");
		//for(string word:words)
		//functionUpdater(word);
	}
	double Calculator(double x)
	{
		fx=constant;
		double exp;
		int pre_def_func[]=new int[2];
		for(int i=top;i>=0;i--)
		{
			exp=coeff[i]*Math.pow(x,pow[i]);//updation required
			pre_def_func[0]=(int)Math.floor(pre_def[i]);
			//System.out.println(pre_def[i]);
			if(pre_def_func[0]!=0)
				pre_def_func[1]=(int)((pre_def[i]-pre_def_func[0])*10)+1;
			else
				pre_def_func[1]=(int)((pre_def[i]-pre_def_func[0])*10);
			//System.out.println(pre_def_func[0]+" "+pre_def_func[1]);
			if(pre_def_func[0]==-2)//-1
				fx+=exp;
			else if(pre_def_func[0]==0)
				fx+=exp*Math.pow(Math.sin(x),pre_def_func[1]);
			else if(pre_def_func[0]==4)
				fx+=exp*Math.pow(Math.cos(x),pre_def_func[1]);
			else if(pre_def_func[0]==2)
				fx+=exp*Math.pow(Math.tan(x),pre_def_func[1]);
			else if(pre_def_func[0]==1)
				fx+=exp*1.0/Math.pow(Math.sin(x),pre_def_func[1]);
			else if(pre_def_func[0]==3)
				fx+=exp*1.0/Math.pow(Math.cos(x),pre_def_func[1]);
			else
				fx+=exp*1.0/Math.pow(Math.tan(x),pre_def_func[1]);
		}
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
		System.out.println("Enter the Equation in constant[variable]^power[Trigonometric function]^power[variable]+next term format");
		String func=sc.next();
		Convert_To_Function Fx=new Convert_To_Function(func,v);
		//Fx.wordExtractor();
		System.out.println("Enter the value of the variable: ");
		double x=sc.nextDouble();
		System.out.println("The output is: "+Fx.Calculator(x));
		sc.close();
	}
}

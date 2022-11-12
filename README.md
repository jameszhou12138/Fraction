# Fraction
## 实验题目

分数类           

## 实验环境

Intellij IDEA、记事本、命令提示符等   

## 实验要求

1. 掌握类、对象、类成员变量、对象成员方法、对象的构造方法、类静态变量和类静态方法。

2. 掌握数组的使用、掌握访问权限。

3. 掌握Object类的重要方法，包括toString()和clone()方法。

4. 实验实现目标：实现分数类及其运算，能够实现任意的分数的单次的和批量的正确、高效的运算。

## 实验内容

1. 实验背景介绍

​	（1）分数是指两个整数的比。分数本身可以构造和约分；任何整数都可以看作一个分数。

​	（2）单个分数可以运算，包括求倒数、求相反数、扩大、缩小等。

​	（3）两个分数可以运算，包括加减乘除等。

2. 用记事本书写一个Java程序

​	（1）建立个人子目录

步骤1：建立个人子目录

​	第一次上机时先在D盘上建立一个以自己学号+姓名为目录名的子目录实验完成的源代码、Java字节码和实验报告三个文件都要放在这个文件夹下。

步骤2：建立Java源代码文件

​	在所建立的文件夹下建立一个记事本文件Fraction.txt，并把它重命名为Fraction.java

​	（2）编写源代码

步骤1：创建一个公共类Fraction

​	要创建的公共类在默认包中，可引入其它的包。所创建的公共类在文件中的一行如下：

```java
public class Fraction{… …}
```

步骤2：建立主方法main( )

​	在类Fraction的类体中编写主方法：

```java
public static void main(String[] args){… …}
```

步骤3：编写方法和Fraction的主方法main( )

​	主方法用于测试。

 

3. 调试和运行

（1）调试

​	步骤1：使用命令行工具，先进入用所建的目录下。参见图1。

​	步骤2：用javac Fraction.java编译并调试源代码文件。参见图1。

（2）运行

​	使用java Fraction运行程序。参见图1。

​	运行说明：

​		第一次，让main()方法调用测试方法testSimple()，然后编译运行；

​		第二次，让main()方法调用测试方法testComplex()，然后编译运行。运行情况如图1所示。

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17052\wps1.jpg) 

图1 编译和运行Fraction.class程序

 

 

## 实验方法

在实验任务书中已经给出的代码框架的基础上，对分数类Fraction的对象、类成员变量、对象成员方法、对象的构造方法、类静态变量、类静态方法、测试方法等方法的代码进行编写。

 

## 实验结果

（1）测试方法testSimple中的内容为计算1/(1*2) + 1/(2*3) + ... + 1/(100*101)的值，根据计算可得理论上最终的结果应该为100/101。让main()方法调用测试方法testSimple，然后编译运行，输出”+100/101”。（如图1所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17052\wps2.jpg) 

图1 编译运行测试方法testSimple

（2）测试方法testComplex中的内容为计算[1/(1*2) + 1/(2*3) + ... + 1/(2022*2023)] - [1/(1*3) + 1/(3*5) + ... + 1/(2021*2023)]的值，根据计算可得理论上最终的结果应该为1011/2023。我们把所有的项存起来，完全随机打乱顺序，用来测试 Fraction 类的计算正确性和计算能力。让main()方法调用测试方法testSimple，然后编译运行，输出”+1011/2023”。（如图2所示）

![img](file:///C:\Users\ZHOUZI~1\AppData\Local\Temp\ksohtml17052\wps3.jpg) 

图2 编译运行测试方法testComplex



## 结论分析

（1）构造方法

① 由两个大整数构造一个分数：

先判断两个大整数是否为0：若分子分母都为0，则为分数常量0/0；若分子为0，分母不为0，则为分数常量0/1；若分子大于0，分母为0，则为分数常量+1/0；若分子小于0，分母为0，则为分数常量-1/0。

若分子分母都不为0，分数的符号与分子分母相乘的符合相同，将分子分母都取绝对值，最后进行约分，得到化简后的分子和分母。

```java
public Fraction(BigInteger primNumerator, BigInteger primDenominator) {
  if (primNumerator.equals(BigInteger.ZERO) && primDenominator.equals(BigInteger.ZERO)){
    this.numerator = BigInteger.ZERO;
    this.denominator = BigInteger.ZERO;
    this.sign = 0;
  }else if (primNumerator.equals(BigInteger.ZERO)){
    this.numerator = BigInteger.ZERO;
    this.denominator = BigInteger.ONE;
    this.sign = 0;
  }else if (primDenominator.equals(BigInteger.ZERO)){
    this.numerator = BigInteger.ONE;
    this.denominator = BigInteger.ZERO;
    if (primNumerator.compareTo(BigInteger.ZERO) > 0){
      this.sign = 1;
    }else{
      this.sign = -1;
    }
  }else{
    this.sign = primNumerator.multiply(primDenominator).signum();
    primNumerator = primNumerator.abs();
    primDenominator = primDenominator.abs();
    BigInteger gcd = primNumerator.gcd(primDenominator);
    this.numerator = primNumerator.divide(gcd);
    this.denominator = primDenominator.divide(gcd);
  }
}
```

 

② 由两个两个长整型构造一个分数：

先判断两个长整型是否为0：若分子分母都为0，则为分数常量0/0；若分子为0，分母不为0，则为分数常量0/1；若分子大于0，分母为0，则为分数常量+1/0；若分子小于0，分母为0，则为分数常量-1/0。

若分子分母都不为0，将长整型的分子和分母分别转换为大整数。分数的符号与分子分母相乘的符合相同，将分子分母都取绝对值，最后进行约分，得到化简后的分子和分母。

```java
public Fraction(long lNumerator, long lDenominator) {
  if (lNumerator == 0 && lDenominator == 0){
    this.numerator = BigInteger.ZERO;
    this.denominator = BigInteger.ZERO;
    this.sign = 0;
  }else if (lNumerator == 0){
    this.numerator = BigInteger.ZERO;
    this.denominator = BigInteger.ONE;
    this.sign = 0;
  }else if (lDenominator == 0){
    this.numerator = BigInteger.ONE;
    this.denominator = BigInteger.ZERO;
    if (lNumerator > 0){
      this.sign = 1;
    }else{
      this.sign = -1;
    }
  }else{
    if (lNumerator * lDenominator > 0){
      this.sign = 1;
    }else{
      this.sign = -1;
    }
    BigInteger newNumerator = new BigInteger(String.valueOf(Math.abs(lNumerator)));
    BigInteger newDenominator = new BigInteger(String.valueOf(Math.abs(lDenominator)));
    BigInteger gcd = newNumerator.gcd(newDenominator);
    newNumerator = newNumerator.divide(gcd);
    newDenominator = newDenominator.divide(gcd);
    this.numerator = newNumerator;
    this.denominator = newDenominator;
  }
}
```

 

③用一个长整数构造一个分数，默认分数的分母是1：

直接调用②的构造方法，传入的值分别为长整数和1。

```java
public Fraction(long integerValue) {
  this(integerValue, 1);
}
```

 

（2）返回私有成员方法：

```java
//获取本分数的正负号，用-1、0、1表示。
public int getSign() {
  return this.sign;
}

//取本分数的分子，注意分子恒为非负。
public BigInteger getNumerator() {
  return this.numerator;
}

//获取本分数的分母，注意分母恒为非负。
public BigInteger getDenominator() {
  return this.denominator;
}
```

 

（3）格式化输出方法：

定义一个字符串，先将分子和分母的字符串类型用”/”相连。当分数的符号为1时，在字符串的最前面加一个”+”；当分数的符号为-1时，在字符串的最前面加一个”-”，最终返回该字符串。

```java
public String toString() {
  String ans = this.numerator.toString() + "/" + this.denominator.toString();
  if (this.sign > 0){
    ans = "+" + ans;
  }else if (this.sign < 0){
    ans = "-" + ans;
  }
  return ans;
}
```

 

（4）判断方法：

```java
//判断此分数是否为“非数”，也就是是否是“0/0”类型。
public boolean isNaN() {
  if (this.numerator.equals(BigInteger.ZERO) && this.denominator.equals(BigInteger.ZERO)){
    return true;
  }else{
    return false;
  }
}

//判断此分数是否为0，也就是是否为“0/1”这各类型的分数。
public boolean isZero() {
  if (this.numerator.equals(BigInteger.ZERO) && ! this.denominator.equals(BigInteger.ZERO)){
    return true;
  }else{
    return false;
  }
}

//判断此分数是否是正的分数；当然，正无穷也是正的分数。
public boolean isPositive() {
  if (this.sign > 0){
    return true;
  }else{
    return false;
  }
}

//判断此分数是否为负的，当然负无穷也是负的。
public boolean isNegative() {
  // Add your code here
  if (this.sign < 0){
    return true;
  }else{
    return false;
  }
}

//判断此分数是否为无穷大，包括正无穷和负无穷。
public boolean isInfinite() {
  // Add your code here
  if (! this.numerator.equals(BigInteger.ZERO) && this.denominator.equals(BigInteger.ZERO)){
    return true;
  }else{
    return false;
  }
}
```

 

（5）常见运算方法：

1) 拷贝方法：

在堆中创建一个新的Fraction对象，并将当前分数的分子分母和符号赋给新建的分数，返回新建的分数。

```java
//把本分数的内容全部拷贝，生成一个新的分数返回。
@Override
public Fraction clone() {
  Fraction ans = new Fraction(this.numerator, this.denominator);
  ans.sign = this.sign;
  return ans;
}
```



2) 求分数的绝对值方法：

对分数的符号求其绝对值后，返回该分数。

```java
//求本分数的绝对值并返回。
public Fraction abs() {
  this.sign = Math.abs(this.sign);
  return this;
}
```



3) 求分数的相反数方法：

对分数的符号取反后，返回该分数。

```java
//求本分数的相反数，也是一个分数。
public Fraction opposite() {
  this.sign = - this.sign;
  return this;
}
```

 

4) 求分数的倒数方法：

先对特殊情况进行判断：若分数是分数常量0/0，则返回分数常量0/0；若分数是分数常量0/1，则返回分数常量+1/0；若分数是分数常量1/0或者-1/0，则返回分数常量0/1。

其他情况就交换分子和分母后，返回该分数。

```java
//求本分数的倒数，也是一个分数。正负无穷的倒数都是0，但0的倒数规定为正无穷而不是负无穷。
public Fraction reciprocal() {
  if (this.isNaN()){
    return Fraction.NAN;
  }else if (this.isInfinite()){
    return Fraction.ZERO;
  }else{
    BigInteger tmp = this.numerator;
    this.numerator = this.denominator;
    this.denominator = tmp;
    if (this.denominator.equals(BigInteger.ZERO)){
      sign = 1;
    }
    if (this.numerator.equals(BigInteger.ZERO)){
      sign = 0;
    }
    return this;
  }
}
```

 

5) 比较分数的绝对值大小方法：

为方便描述，称本分数为a、要与本分数进行比较的分数为b。

先对特殊情况进行判断：若a和b都是分数常量0/0或0/1中的一个，则返回0；若a是分数常量0/0或0/1，则返回-1；若b是分数常量0/0或0/1，则返回1；若a是分数常量1/0或-1/0，则返回1；若b是分数常量1/0或-1/0，则返回-1。

其他情况就返回a的分子乘b的分母与a的分母乘b的分子进行比较的结果。

```java
//绝对值比较大小。本分数的绝对值和that的绝对值比较大小。
private int absCompareTo(Fraction that) {
  if ((this.isNaN() || this.isZero()) && (that.isNaN() || that.isZero())){
    return 0;
  }else if (this.isNaN() || this.isZero()){
    return -1;
  }else if (that.isNaN() || that.isZero()){
    return 1;
  }else if (this.isInfinite() && that.isInfinite()){
    return 0;
  }else if (this.isInfinite()){
    return 1;
  }else if (that.isInfinite()){
    return -1;
  }else{
    return this.numerator.multiply(that.denominator).compareTo(that.numerator.multiply(this.denominator));
  }
}
```

 

6) 比较分数的大小方法：

为方便描述，称本分数为a、要与本分数进行比较的分数为b。

若a的符号大于b的符号，则返回1；若a的符号小于b的符号，则返回-1。

其他情况就返回a和b的绝对值比较再乘以符号的结果。

```java
//两个分数比较大小。
@Override
public int compareTo(Fraction that) {
  if (this.sign > that.sign){
    return 1;
  }else if (this.sign < that.sign){
    return -1;
  }else{
    return this.sign * this.absCompareTo(that);
  }
}
```

 

7) 两个非负分数相加方法：

为方便描述，称本分数为a、要与本分数进行运算的分数为b、计算的最终结果分数为ans。

ans的分子为a的分子乘以b的分母与b的分子乘以a的分母的和，ans的分母为a的分母乘以b的分母。最后ans的分子和分母进行约分，返回ans。

```java
//两个非负分数相加
private Fraction positiveAdd(Fraction that) {
  BigInteger newNumrator = this.numerator.multiply(that.denominator).add(that.numerator.multiply(this.denominator));
  BigInteger newDenominator = this.denominator.multiply(that.denominator);
  BigInteger gcd = newNumrator.gcd(newDenominator);
  newNumrator = newNumrator.divide(gcd);
  newDenominator = newDenominator.divide(gcd);
  Fraction ans = new Fraction(newNumrator, newDenominator);
  this.numerator = newNumrator;
  this.denominator = newDenominator;
  return this;
}
```

 

8) 两个非负分数相加方法：

为方便描述，称本分数为a、要与本分数进行运算的分数为b、计算的最终结果分数为ans。

ans的分子为a的分子乘以b的分母与b的分子乘以a的分母的差，ans的分母为a的分母乘以b的分母。最后ans的分子和分母进行约分，返回ans。

```java
//两个非负的分数相减。
private Fraction positiveSubtract(Fraction smaller) {
  BigInteger newNumrator = this.numerator.multiply(smaller.denominator).subtract(smaller.numerator.multiply(this.denominator));
  BigInteger newDenominator = this.denominator.multiply(smaller.denominator);
  BigInteger gcd = newNumrator.gcd(newDenominator);
  newNumrator = newNumrator.divide(gcd);
  newDenominator = newDenominator.divide(gcd);
  this.numerator = newNumrator;
  this.denominator = newDenominator;
  return this;
}
```

 

9) 两个分数相加方法：

为方便描述，称本分数为a、要与本分数进行运算的分数为b、计算的最终结果分数为ans。

若a的符号和b的符号相同，则ans为a与b做非负数的相加；若a和b的符号不同，当a大于b时ans为a与b做非负数的减法，当a大于b时ans为b与a做非负数的减法，当a等于b时ans为分数常量0/1。返回ans。

```java
//两个分数相加。
public Fraction add(Fraction that) {
  if (this.sign == that.sign){
    return this.positiveAdd(that);
  }else{
    if (this.absCompareTo(that) > 0){
      return this.positiveSubtract(that);
    }else if (this.absCompareTo(that) < 0){
      return that.positiveSubtract(this);
    }else{
      return Fraction.ZERO;
    }
  }
}
```

 

10) 两个分数相减方法：

为方便描述，称本分数为a、要与本分数进行运算的分数为b、计算的最终结果分数为ans。

若a的符号和b的符号不同，则ans为a与b做非负数的相加；若a和b的符号相同，当a大于b时ans为a与b做非负数的减法，当a大于b时ans为b与a做非负数的减法，当a等于b时ans为分数常量0/1。返回ans。

```java
//两个分数相减。
public Fraction subtract(Fraction that) {
  if (this.sign == that.sign){
    if (this.absCompareTo(that) > 0){
      return this.positiveSubtract(that);
    }else if (this.absCompareTo(that) < 0){
      return that.positiveSubtract(this);
    }else{
      return Fraction.ZERO;
    }
  }else{
    return this.positiveAdd(that);
  }
}
```

 

11) 两个分数相乘方法：

为方便描述，称本分数为a、要与本分数进行运算的分数为b、计算的最终结果分数为ans。

若a或b为分数常量0/0时，返回分数常量0/0。否则，ans的分子为a的分子乘以b的分子，ans的分母为a的分母乘以b的分母，ans的分子和分母进行约分，ans的符号为a的符号乘以b的符号，返回ans。

```java
//一个分数和另一个分数相乘。
public Fraction multiply(Fraction factor) {
  if (factor.compareTo(Fraction.NAN) == 0){
    return NAN;
  }else{
    BigInteger newNumrator = this.numerator.multiply(factor.numerator);
    BigInteger newDenominator = this.denominator.multiply(factor.denominator);
    BigInteger gcd = newNumrator.gcd(newDenominator);
    newNumrator = newNumrator.divide(gcd);
    newDenominator = newDenominator.divide(gcd);
    this.numerator = newNumrator;
    this.denominator = newDenominator;
    this.sign *= factor.sign;
    return this;
  }
}

//本分数乘以一个长整数因子，获取一个新的分数。
public Fraction multiply(long ratio) {
  Fraction factor = new Fraction(ratio, 1);
  return this.multiply(factor);
}
```

 

12) 两个分数相除方法：

为方便描述，称本分数为a、要与本分数进行运算的分数为b、计算的最终结果分数为ans。

若a或b为分数常量0/0或0/1时，返回分数常量0/0。否则，ans的分子为a的分子乘以b的分母，ans的分母为a的分母乘以b的分子，ans的分子和分母进行约分，ans的符号为a的符号乘以b的符号，返回ans。

```java
//获取本分数除以另外一个分数的商。
public Fraction divide(Fraction divisor) {
  if (divisor.compareTo(Fraction.NAN) == 0 || divisor.compareTo(Fraction.ZERO) == 0){
    return Fraction.NAN;
  }
  BigInteger newNumrator = this.numerator.multiply(divisor.denominator);
  BigInteger newDenominator = this.denominator.multiply(divisor.numerator);
  BigInteger gcd = newNumrator.gcd(newDenominator);
  newNumrator = newNumrator.divide(gcd);
  newDenominator = newDenominator.divide(gcd);
  this.numerator = newNumrator;
  this.denominator = newDenominator;
  this.sign *= divisor.sign;
  return this;
}

//获取本分数除以一个长整数因子后的商。
public Fraction divide(long ratio) {
  Fraction factor = new Fraction(ratio, 1);
  return this.divide(factor);
}
```

 

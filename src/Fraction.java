import java.math.BigInteger;
import java.sql.Struct;

/**
 * 此类Fractional是一个用于分数表示和运算的类。
 * 该类基于大整数类BigInteger。
 */
public class Fraction implements Comparable<Fraction>{
    private int sign = 0; // 分数的正负号，正数1，负数-1，零0
    private BigInteger numerator = BigInteger.ZERO; // 分数的分子
    private  BigInteger denominator = BigInteger.ZERO; // 分数的分母
    public static final Fraction ZERO = new Fraction(0, 1); // 分数常量 0/1
    public static final Fraction ONE = new Fraction(1, 1); // 分数常量 +1/1
    public static final Fraction NEGATIVE_INFINITY = new Fraction(-1, 0); // 分数常量 -1/0，即负无穷
    public static final Fraction POSITIVE_INFINITY = new Fraction(1, 0); // 分数常量 +1/0，即正无穷
    public static final Fraction NAN = new Fraction(0, 0); // 分数常量，0/0，即：不是一个分数

    /**
     * 构造方法：由两个大整数构造一个分数，大整数带正负号，最终的分数的正负号通过计算获得。
     * 需要进行处理，包括符号处理，也包括约分处理。
     * @param primNumerator 输入的分子，最终的构造的分数的分子很可能不是这个输入。
     * @param primDenominator 输入的分母，最终的构造的分数的分母很有可能不是这个输入。
     */
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

    /**
     * 用两个长整型构造一个分数。
     * @param lNumerator 输入的长整数分子。
     * @param lDenominator 输入的长整数分母。
     */
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

    /**
     * 获取本分数的正负号，用-1、0、1表示。
     * @return 本分数的正负号。
     */
    public int getSign() {
        return this.sign;
    }

    /**
     * 获取本分数的分子，注意分子恒为非负。
     * @return 本分数的分子。
     */
    public BigInteger getNumerator() {
        return this.numerator;
    }

    /**
     * 获取本分数的分母。注意分母恒为非负。
     * @return 本分数的分母。
     */
    public BigInteger getDenominator() {
        return this.denominator;
    }

    /**
     * 用一个长整数构造一个分数，默认分数的分母是1。
     * @param integerValue 输入的长整数，其绝对值与构造生成的分数的分子相同。
     */
    public Fraction(long integerValue) {
        this(integerValue, 1);
    }

    /**
     * 用来描述这个分数的字符串，例如“-7/2”，“+1/0”等等。
     * @return
     */
    @Override
    public String toString() {
        String ans = this.numerator.toString() + "/" + this.denominator.toString();
        if (this.sign > 0){
            ans = "+" + ans;
        }else if (this.sign < 0){
            ans = "-" + ans;
        }
        return ans;
    }

    /**
     * 判断此分数是否为“非数”，也就是是否是“0/0”类型。
     * @return 如果是一个非数，返回true，否则返回false
     */
    public boolean isNaN() {
        if (this.numerator.equals(BigInteger.ZERO) && this.denominator.equals(BigInteger.ZERO)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断此分数是否为0，也就是是否为“0/1”这各类型的分数。
     * @return 是的话返回true；否则返回false
     */
    public boolean isZero() {
        if (this.numerator.equals(BigInteger.ZERO) && ! this.denominator.equals(BigInteger.ZERO)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断此分数是否是正的分数；当然，正无穷也是正的分数。
     * @return 是的话返回true，否则返回false。
     */
    public boolean isPositive() {
        if (this.sign > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断此分数是否为负的，当然负无穷也是负的。
     * @return 如果是负分数，则返回true，否则返回false。
     */
    public boolean isNegative() {
        if (this.sign < 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断此分数是否为无穷大，包括正无穷和负无穷。
     * @return 如果是无穷大，则返回true，否则返回false。
     */
    public boolean isInfinite() {
        if (! this.numerator.equals(BigInteger.ZERO) && this.denominator.equals(BigInteger.ZERO)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 把本分数的内容全部拷贝，生成一个新的分数返回。
     * @return 本分数的一个复制版本。
     */
    @Override
    public Fraction clone() {
        Fraction ans = new Fraction(this.numerator, this.denominator);
        ans.sign = this.sign;
        return ans;
    }

    /**
     * 求本分数的绝对值并返回。
     * @return 本分数的绝对值，也是一个分数。
     */
    public Fraction abs() {
        this.sign = Math.abs(this.sign);
        return this;
    }

    /**
     * 求本分数的相反数，也是一个分数。
     * @return 本分数的相反数。
     */
    public Fraction opposite() {
        this.sign = - this.sign;
        return this;
    }

    /**
     * 求本分数的倒数，也是一个分数。正负无穷的倒数都是0，但0的倒数规定为正无穷而不是负无穷。
     * @return 本分数的倒数。
     */
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

    /**
     * 绝对值比较大小。本分数的绝对值和that的绝对值比较大小。
     * 把NaN当作0处理；正无穷和正无穷比较为相等。
     * @param that 与本分数待比较的分数。
     * @return 当本分数的绝对值和that的绝对值相等时返回0；本分数绝对值小的时候返回-1，绝对值大的时候返回1。
     */
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

    /**
     * 两个分数比较大小。
     * @param that 与本分数待比较的分数。
     * @return 如果两个分数值相等，返回0；如果本分数小，返回-1；否则返回1。
     */
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

    /**
     * 两个非负分数相加。
     * @param that 与本分数待相加的分数。
     * @return 两个分数相加的和，仍然时一个分数。
     */
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

    /**
     * 两个非负的分数相减。
     * @param smaller 这是减数，本分数时被减数。
     * @return 返回两个分数的差。
     */
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

    /**
     * 两个分数相加。
     * @param that 加数。本分数是被加数。
     * @return 两个分数的加和。
     */
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

    /**
     * 两个分数相减。
     * @param that 减数。
     * @return 本分数减去that的差。
     */
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

    /**
     * 一个分数和另一个分数相乘。
     * @param factor 乘数。本分数是被乘数。
     * @return 两个分数的积，仍然是一个分数。
     */
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

    /**
     * 本分数乘以一个长整数因子，获取一个新的分数。
     * @param ratio 待乘的因子。
     * @return 放大后新的分数。
     */
    public Fraction multiply(long ratio) {
        Fraction factor = new Fraction(ratio, 1);
        return this.multiply(factor);
    }

    /**
     * 获取本分数除以另外一个分数的商。
     * @param divisor 除数。
     * @return 商。
     */
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

    /**
     * 获取本分数除以一个长整数因子后的商。
     * @param ratio 除数，一个长整数因子。
     * @return 商。
     */
    public Fraction divide(long ratio) {
        Fraction factor = new Fraction(ratio, 1);
        return this.divide(factor);
    }

    /**
     * 一个简单的测试。计算 1/(1*2) + 1/(2*3) + ... + 1/(100*101)
     * 事实上，上式等于 (1/1 - 1/2) + (1/2 - 1/3) + ... + (1/100 - 1/101)
     * 最后的结果应该是 "+100/101"。
     */
    public static void testSimple() {
        Fraction sum = ZERO;
        for (int i=1; i<=100; i++) {
            int denominator = i * (i+1);
            Fraction addend = new Fraction(1, denominator);
            sum = sum.add(addend);
        }
        System.out.println(sum);
    }

    /**
     * 一个复杂的测试：计算 [ 1/(1*2) + 1/(2*3) + ... + 1/(2022*2023) ] - [ 1/(1*3) + 1/(3*5) + ... + 1/(2021*2023) ]
     * 被减数最后的结果是 +2022/2023，减数最后的结果是 1011/2023，最终的结果应该是 1011/2023。
     * 我们把所有的项存起来，完全随机打乱顺序，用来测试 Fraction 类的计算正确性和计算能力。
     */
    public static void testComplex() {
        java.util.ArrayList<Fraction> al = new java.util.ArrayList<Fraction>();
        int quantity = 2022;
        for (int i=1; i<=quantity; i++) {
            int denominator = i * (i+1);
            Fraction addend = new Fraction(1, denominator);
            al.add(addend);
        }
        for (int i=1; i<=quantity-1; i+=2) {
            int denominator = i * (i+2);
            Fraction addend = new Fraction(1, denominator);
            al.add(addend.opposite());
        }
        int size = al.size();
        Fraction[] fractions = new Fraction[size];
        al.toArray(fractions);
        for (int i=0; i<size; i++) {
            int pos = (int)(Math.random() * size);
            Fraction temp = fractions[i];
            fractions[i] = fractions[pos];
            fractions[pos] = temp;
        }
        Fraction sum = Fraction.ZERO;
        for (int i=0; i<size; i++)
            sum = sum.add(fractions[i]);
        System.out.println(sum);
    }

    public static void myTest() {
        Fraction x = new Fraction(-1, 2);
        Fraction y = x.clone();
        System.out.println(x + " " + y);
        x = x.reciprocal();
        y = y.abs();
        System.out.println(x + " " + y);
        x.numerator = BigInteger.valueOf(100);
        y.denominator = BigInteger.valueOf(123123);
        System.out.println(x + " " + y);
        int flag = Fraction.ZERO.compareTo(Fraction.NAN);
        System.out.println(flag);

        //计算 (+1/1) * [(+1/2) * (+2/3) * ... * (+2022/2023)] / [ (-2022/2023) * (-2021/2022) * ... * (-1/2)] = +1/1
        int quantity = 2022;
        Fraction ans = new Fraction(1);
        for (int i = 1; i <= quantity; i++){
//            System.out.print("ans:" + ans);
            Fraction tmp1 = new Fraction(i, i + 1);
            ans.multiply(tmp1);
//            System.out.print("\ttmp1:" + tmp1 + "\tans:" + ans);
            Fraction tmp2 = new Fraction(quantity - i + 1, quantity - i + 2);
            ans.divide(tmp2.opposite());
//            System.out.println("\ttmp2:" + tmp2 + "\tans:" + ans);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
//        testSimple();
        testComplex();
//        myTest();
    }
}
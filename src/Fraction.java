import java.math.BigInteger;
import java.sql.Struct;

/**
 * ����Fractional��һ�����ڷ�����ʾ��������ࡣ
 * ������ڴ�������BigInteger��
 */
public class Fraction implements Comparable<Fraction>{
    private int sign = 0; // �����������ţ�����1������-1����0
    private BigInteger numerator = BigInteger.ZERO; // �����ķ���
    private  BigInteger denominator = BigInteger.ZERO; // �����ķ�ĸ
    public static final Fraction ZERO = new Fraction(0, 1); // �������� 0/1
    public static final Fraction ONE = new Fraction(1, 1); // �������� +1/1
    public static final Fraction NEGATIVE_INFINITY = new Fraction(-1, 0); // �������� -1/0����������
    public static final Fraction POSITIVE_INFINITY = new Fraction(1, 0); // �������� +1/0����������
    public static final Fraction NAN = new Fraction(0, 0); // ����������0/0����������һ������

    /**
     * ���췽��������������������һ���������������������ţ����յķ�����������ͨ�������á�
     * ��Ҫ���д����������Ŵ���Ҳ����Լ�ִ���
     * @param primNumerator ����ķ��ӣ����յĹ���ķ����ķ��Ӻܿ��ܲ���������롣
     * @param primDenominator ����ķ�ĸ�����յĹ���ķ����ķ�ĸ���п��ܲ���������롣
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
     * �����������͹���һ��������
     * @param lNumerator ����ĳ��������ӡ�
     * @param lDenominator ����ĳ�������ĸ��
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
     * ��ȡ�������������ţ���-1��0��1��ʾ��
     * @return �������������š�
     */
    public int getSign() {
        return this.sign;
    }

    /**
     * ��ȡ�������ķ��ӣ�ע����Ӻ�Ϊ�Ǹ���
     * @return �������ķ��ӡ�
     */
    public BigInteger getNumerator() {
        return this.numerator;
    }

    /**
     * ��ȡ�������ķ�ĸ��ע���ĸ��Ϊ�Ǹ���
     * @return �������ķ�ĸ��
     */
    public BigInteger getDenominator() {
        return this.denominator;
    }

    /**
     * ��һ������������һ��������Ĭ�Ϸ����ķ�ĸ��1��
     * @param integerValue ����ĳ������������ֵ�빹�����ɵķ����ķ�����ͬ��
     */
    public Fraction(long integerValue) {
        this(integerValue, 1);
    }

    /**
     * ������������������ַ��������硰-7/2������+1/0���ȵȡ�
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
     * �жϴ˷����Ƿ�Ϊ����������Ҳ�����Ƿ��ǡ�0/0�����͡�
     * @return �����һ������������true�����򷵻�false
     */
    public boolean isNaN() {
        if (this.numerator.equals(BigInteger.ZERO) && this.denominator.equals(BigInteger.ZERO)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * �жϴ˷����Ƿ�Ϊ0��Ҳ�����Ƿ�Ϊ��0/1��������͵ķ�����
     * @return �ǵĻ�����true�����򷵻�false
     */
    public boolean isZero() {
        if (this.numerator.equals(BigInteger.ZERO) && ! this.denominator.equals(BigInteger.ZERO)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * �жϴ˷����Ƿ������ķ�������Ȼ��������Ҳ�����ķ�����
     * @return �ǵĻ�����true�����򷵻�false��
     */
    public boolean isPositive() {
        if (this.sign > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * �жϴ˷����Ƿ�Ϊ���ģ���Ȼ������Ҳ�Ǹ��ġ�
     * @return ����Ǹ��������򷵻�true�����򷵻�false��
     */
    public boolean isNegative() {
        if (this.sign < 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * �жϴ˷����Ƿ�Ϊ����󣬰���������͸����
     * @return �����������򷵻�true�����򷵻�false��
     */
    public boolean isInfinite() {
        if (! this.numerator.equals(BigInteger.ZERO) && this.denominator.equals(BigInteger.ZERO)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * �ѱ�����������ȫ������������һ���µķ������ء�
     * @return ��������һ�����ư汾��
     */
    @Override
    public Fraction clone() {
        Fraction ans = new Fraction(this.numerator, this.denominator);
        ans.sign = this.sign;
        return ans;
    }

    /**
     * �󱾷����ľ���ֵ�����ء�
     * @return �������ľ���ֵ��Ҳ��һ��������
     */
    public Fraction abs() {
        this.sign = Math.abs(this.sign);
        return this;
    }

    /**
     * �󱾷������෴����Ҳ��һ��������
     * @return ���������෴����
     */
    public Fraction opposite() {
        this.sign = - this.sign;
        return this;
    }

    /**
     * �󱾷����ĵ�����Ҳ��һ����������������ĵ�������0����0�ĵ����涨Ϊ����������Ǹ����
     * @return �������ĵ�����
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
     * ����ֵ�Ƚϴ�С���������ľ���ֵ��that�ľ���ֵ�Ƚϴ�С��
     * ��NaN����0�����������������Ƚ�Ϊ��ȡ�
     * @param that �뱾�������Ƚϵķ�����
     * @return ���������ľ���ֵ��that�ľ���ֵ���ʱ����0������������ֵС��ʱ�򷵻�-1������ֵ���ʱ�򷵻�1��
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
     * ���������Ƚϴ�С��
     * @param that �뱾�������Ƚϵķ�����
     * @return �����������ֵ��ȣ�����0�����������С������-1�����򷵻�1��
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
     * �����Ǹ�������ӡ�
     * @param that �뱾��������ӵķ�����
     * @return ����������ӵĺͣ���Ȼʱһ��������
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
     * �����Ǹ��ķ��������
     * @param smaller ���Ǽ�����������ʱ��������
     * @return �������������Ĳ
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
     * ����������ӡ�
     * @param that �������������Ǳ�������
     * @return ���������ļӺ͡�
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
     * �������������
     * @param that ������
     * @return ��������ȥthat�Ĳ
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
     * һ����������һ��������ˡ�
     * @param factor �������������Ǳ�������
     * @return ���������Ļ�����Ȼ��һ��������
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
     * ����������һ�����������ӣ���ȡһ���µķ�����
     * @param ratio ���˵����ӡ�
     * @return �Ŵ���µķ�����
     */
    public Fraction multiply(long ratio) {
        Fraction factor = new Fraction(ratio, 1);
        return this.multiply(factor);
    }

    /**
     * ��ȡ��������������һ���������̡�
     * @param divisor ������
     * @return �̡�
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
     * ��ȡ����������һ�����������Ӻ���̡�
     * @param ratio ������һ�����������ӡ�
     * @return �̡�
     */
    public Fraction divide(long ratio) {
        Fraction factor = new Fraction(ratio, 1);
        return this.divide(factor);
    }

    /**
     * һ���򵥵Ĳ��ԡ����� 1/(1*2) + 1/(2*3) + ... + 1/(100*101)
     * ��ʵ�ϣ���ʽ���� (1/1 - 1/2) + (1/2 - 1/3) + ... + (1/100 - 1/101)
     * ���Ľ��Ӧ���� "+100/101"��
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
     * һ�����ӵĲ��ԣ����� [ 1/(1*2) + 1/(2*3) + ... + 1/(2022*2023) ] - [ 1/(1*3) + 1/(3*5) + ... + 1/(2021*2023) ]
     * ���������Ľ���� +2022/2023���������Ľ���� 1011/2023�����յĽ��Ӧ���� 1011/2023��
     * ���ǰ����е������������ȫ�������˳���������� Fraction ��ļ�����ȷ�Ժͼ���������
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

        //���� (+1/1) * [(+1/2) * (+2/3) * ... * (+2022/2023)] / [ (-2022/2023) * (-2021/2022) * ... * (-1/2)] = +1/1
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
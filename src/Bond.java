import java.text.NumberFormat;

public class Bond extends Security {

	private double principal;
	private double couponRate;
	private double holdingPeriod;

	/**
	 * @param principal The original investment
	 * @param couponRate The interest rate per year
	 * @param holdingPeriod The number of years
	 */
	public Bond(String isin, String issuer, double principal, double couponRate, double holdingPeriod) {
		this.isin = isin;
		this.issuer = issuer;
		this.principal = principal;
		this.couponRate = couponRate;
		this.holdingPeriod = holdingPeriod;
	}

	/**
	 * @return the principal
	 */
	public double getPrincipal() {
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	/**
	 * @return the couponRate
	 */
	public double getCouponRate() {
		return couponRate;
	}

	/**
	 * @param couponRate
	 *            the couponRate to set
	 */
	public void setCouponRate(double couponRate) {
		this.couponRate = couponRate;
	}

	/**
	 * @return the holdingPeriod
	 */
	public double getHoldingPeriod() {
		return holdingPeriod;
	}

	/**
	 * @param holdingPeriod
	 *            the holdingPeriod to set
	 */
	public void setHoldingPeriod(double holdingPeriod) {
		this.holdingPeriod = holdingPeriod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMinimumFractionDigits(2);
		return "Bond [" + isin + ", " + issuer + ", Principal: " + currency.format(principal) + ", Coupon: "
				+ percent.format(couponRate) + ", Holding: " + holdingPeriod + " Years, Total Return: "
				+ currency.format(totalReturn()) + ", Percent Return: " + percent.format(percentReturn()) + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(couponRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(holdingPeriod);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(principal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj)) {
            return false;
        }
		if (getClass() != obj.getClass())
			return false;
		Bond other = (Bond) obj;
		if (Double.doubleToLongBits(couponRate) != Double.doubleToLongBits(other.couponRate))
			return false;
		if (Double.doubleToLongBits(holdingPeriod) != Double.doubleToLongBits(other.holdingPeriod))
			return false;
		if (Double.doubleToLongBits(principal) != Double.doubleToLongBits(other.principal))
			return false;
		return true;
	}

	@Override
	public int compareTo(Security other) {
		
		int issuerDiff = this.issuer.compareTo(other.issuer);
		if (issuerDiff != 0) { return issuerDiff; }
		
		// If both objects are of type Bond, perform a deep compare of all fields.
		if (other instanceof Bond b) {
			// Compare by holding period first
			int holdingDiff = Double.compare(this.holdingPeriod, b.holdingPeriod);
			if (holdingDiff != 0) { return holdingDiff; }

			int principalDiff = Double.compare(this.principal, b.principal);
			if (principalDiff != 0) { return principalDiff; }

			int couponDiff = Double.compare(this.couponRate, b.couponRate);
			if (couponDiff != 0) { return couponDiff; }
		}
		// Otherwise, securities are the "same"
		return this.isin.compareTo(other.isin);
	}

	@Override
	public double percentReturn() {
		return totalReturn() / principal;
	}

	@Override
	public double totalReturn() {
		return principal * couponRate * holdingPeriod;
	}

}

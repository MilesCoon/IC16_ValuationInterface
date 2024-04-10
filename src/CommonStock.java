import java.text.NumberFormat;

public class CommonStock extends Security implements Comparable<Security>, Valuation {

	private int shares;
	private double purchasePrice;
	private double currentPrice;

	/**
	 * @param shares
	 * @param purchasePrice
	 * @param currentPrice
	 */
	public CommonStock(String isin, String issuer, int shares, double purchasePrice, double currentPrice) {
		this.isin = isin;
		this.issuer = issuer;
		this.shares = shares;
		this.purchasePrice = purchasePrice;
		this.currentPrice = currentPrice;
	}

	/**
	 * @return the shares
	 */
	public int getShares() {
		return shares;
	}

	/**
	 * @param shares
	 *            the shares to set
	 */
	public void setShares(int shares) {
		this.shares = shares;
	}

	/**
	 * @return the purchasePrice
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice
	 *            the purchasePrice to set
	 */
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * @return the currentPrice
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * @param currentPrice
	 *            the currentPrice to set
	 */
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
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
		temp = Double.doubleToLongBits(currentPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(purchasePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + shares;
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommonStock other = (CommonStock) obj;
		if (Double.doubleToLongBits(currentPrice) != Double.doubleToLongBits(other.currentPrice))
			return false;
		if (Double.doubleToLongBits(purchasePrice) != Double.doubleToLongBits(other.purchasePrice))
			return false;
		if (shares != other.shares)
			return false;
		return true;
	}

	@Override
	public int compareTo(Security other) {

		int issuerDiff = this.issuer.compareTo(other.issuer);
		if (issuerDiff != 0) { return issuerDiff; }

		// If both objects are of type Bond, perform a deep compare of all fields.
		if (other instanceof CommonStock c) {
			// Compare by holding period first
			int sharesDiff = Double.compare(this.shares, c.shares);
			if (sharesDiff != 0) { return sharesDiff; }

			int purchaseDiff = Double.compare(this.purchasePrice, c.purchasePrice);
			if (purchaseDiff != 0) { return purchaseDiff; }

			int currentDiff = Double.compare(this.currentPrice, c.currentPrice);
			if (currentDiff != 0) { return currentDiff; }
		}
		// Otherwise, securities are the "same"
		return this.isin.compareTo(other.isin);
	}

	@Override
	public double percentReturn() {
		return (currentPrice - purchasePrice) / purchasePrice;
	}

	@Override
	public double totalReturn() {
		return shares * (currentPrice - purchasePrice);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		NumberFormat c = NumberFormat.getCurrencyInstance();
		NumberFormat p = NumberFormat.getPercentInstance();
		p.setMinimumFractionDigits(2);
		return "Common Stock [" + isin + ", " + issuer + ", Purchase: " + c.format(purchasePrice)
				+ ", Current: " + c.format(currentPrice) + ", Shares: " + shares + ", Total Return: " + c.format(totalReturn())
				+ ", Percent Return: " + p.format(percentReturn()) + "]";
	}


}

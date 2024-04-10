public abstract class Security implements Comparable<Security>, Valuation {

	protected String isin;
	protected String issuer;
	
	/**
	 * @return the iSIN
	 */
	public String getISIN() {
		return isin;
	}
	
	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isin == null) ? 0 : isin.hashCode());
		result = prime * result + ((issuer == null) ? 0 : issuer.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Security other = (Security) obj;
		if (isin == null) {
			if (other.isin != null)
				return false;
		} else if (!isin.equals(other.isin))
			return false;
		if (issuer == null) {
			if (other.issuer != null)
				return false;
		} else if (!issuer.equals(other.issuer))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Security other) {

		int isinComp = isin.compareTo(other.isin);
		if (isinComp != 0)
			return isinComp;

		return issuer.compareTo(other.issuer);
		
	}
			
}

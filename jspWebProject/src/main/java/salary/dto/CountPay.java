package salary.dto;

public class CountPay {
	private int countSalary;
	private int mts;
	private int mda;
	private int mts_mda;

	public CountPay(int countSalary, int mts, int mda) {
		if (countSalary < 0) {
			this.countSalary = 0;
			this.mts = 0;
			this.mda = 0;
			this.mts_mda = mts - mda;
		} else {
			this.countSalary = countSalary;
			this.mts = mts;
			this.mda = mda;
			this.mts_mda = mts - mda;
		}

	}

	public int getCountSalary() {
		return countSalary;
	}

	public int getMts() {
		return mts;
	}

	public int getMda() {
		return mda;
	}

	public int getMts_mda() {
		return mts_mda;
	}

}

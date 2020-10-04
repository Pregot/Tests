package bank;

import lt.itakademija.exam.Currency;
import lt.itakademija.exam.CurrencyConversionException;
import lt.itakademija.exam.CurrencyConverter;
import lt.itakademija.exam.CurrencyRatesProvider;
import lt.itakademija.exam.Money;

public class MyCurrencyConverter implements CurrencyConverter {
	private CurrencyRatesProvider ratesProvider;

	public MyCurrencyConverter(CurrencyRatesProvider ratesProvider) {
		this.ratesProvider = ratesProvider;
	}

	@Override
	public Money convert(Currency from, Currency to, Money amount) {
		Money rate = ratesProvider.getRate(from, to);
		if (rate == null) {
			throw new CurrencyConversionException("   ");
		}
		return amount.multiply(rate);
	}

}

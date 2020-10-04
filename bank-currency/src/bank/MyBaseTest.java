package bank;

import lt.itakademija.exam.Bank;
import lt.itakademija.exam.CurrencyConverter;
import lt.itakademija.exam.CurrencyRatesProvider;
import lt.itakademija.exam.test.BaseTest;

public class MyBaseTest extends BaseTest {

	@Override
	protected Bank createBank(CurrencyConverter currencyConverter) {
		Bank pav = new MyBank(currencyConverter);
		return pav;
	}

	@Override
	protected CurrencyConverter createCurrencyConverter(CurrencyRatesProvider ratesProvider) {
		CurrencyConverter pav = new MyCurrencyConverter(ratesProvider);
		return pav;
	}

}

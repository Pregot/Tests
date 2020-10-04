package bank;

import java.util.ArrayList;
import java.util.Collection;

import lt.itakademija.exam.Account;
import lt.itakademija.exam.AccountCreateException;
import lt.itakademija.exam.Bank;
import lt.itakademija.exam.Currency;
import lt.itakademija.exam.CurrencyConverter;
import lt.itakademija.exam.Customer;
import lt.itakademija.exam.CustomerCreateException;
import lt.itakademija.exam.InsufficientFundsException;
import lt.itakademija.exam.Money;
import lt.itakademija.exam.Operation;
import lt.itakademija.exam.PersonCode;
import lt.itakademija.exam.PersonName;
import lt.itakademija.exam.SequenceGenerator;

public class MyBank implements Bank {
	private SequenceGenerator accountnumber = new SequenceGenerator();
	private SequenceGenerator customernumber = new SequenceGenerator();
	private SequenceGenerator transfermoneyid = new SequenceGenerator();
	private CurrencyConverter currencyConverter;
	protected Collection<Customer> list = new ArrayList<Customer>();

	public MyBank(CurrencyConverter newcurrencyConverter) {
		this.currencyConverter = newcurrencyConverter;
	}

	@Override
	public Account createAccount(Customer customer, Currency currency) {
		if (customer == null || currency == null) {
			throw new NullPointerException();
		}
		if (!list.contains(customer)) {
			throw new AccountCreateException(" ");
		}
		Account account = new Account(accountnumber.getNext(), customer, currency, new Money(0.0));
		customer.addAccount(account);
		return account;
	}

	@Override
	public Customer createCustomer(PersonCode personCode, PersonName personName) {
		Customer customer = new Customer(customernumber.getNext(), personCode, personName);
		if (personCode == null || personName == null) {
			throw new NullPointerException();
		}
		for (Customer customers : this.list) {
			if (customers.getPersonCode().equals(personCode)) {
				throw new CustomerCreateException(" ");
			}
		}
		this.list.add(customer);

		return customer;
	}

	@Override
	public Money getBalance(Currency currency) {
		Money balance = new Money(0);
		for (Customer customers : this.list) {
			for (Account accounts : customers.getAccounts()) {
				if (accounts.getCurrency().equals(currency)) {
					balance = balance.add(accounts.getBalance());
				} else {
					balance = balance
							.add(currencyConverter.convert(accounts.getCurrency(), currency, accounts.getBalance()));
				}
			}
		}

		return balance;
	}

	@Override
	public Operation transferMoney(Account debitAccount, Account creditAccount, Money debitAmount) {
		if (debitAccount.getBalance().isLessThan(debitAmount)) {
			throw new InsufficientFundsException(" ");
		}
		debitAccount.setBalance(debitAccount.getBalance().substract(debitAmount));
		Money creditAmount = currencyConverter.convert(debitAccount.getCurrency(), creditAccount.getCurrency(),
				debitAmount);
		creditAccount.setBalance(creditAccount.getBalance().add(creditAmount));
		return new Operation(transfermoneyid.getNext(), debitAccount, creditAccount, debitAmount);
	}

}

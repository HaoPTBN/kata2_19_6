package org.qsoft.tdd.kata2;

import ogr.qsoft.tdd.entity.BankAccount;
import ogr.qsoft.tdd.entity.DetailDeposit;

import javax.xml.soap.Detail;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 6/12/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountService {

    private BankAccountDao bankAccountDao;
    private DetailDepositDao detailDepositDao;
    private List<DetailDeposit> mockList;
    private Date dateTimes;

    public Date getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(Date dateTimes) {
        this.dateTimes = dateTimes;
    }

    public List<DetailDeposit> getMockList() {
        return mockList;
    }

    public void setMockList(List<DetailDeposit> mockList) {
        this.mockList = mockList;
    }

    public DetailDepositDao getDetailDepositDao() {
        return detailDepositDao;
    }

    public void setDetailDepositDao(DetailDepositDao detailDepositDao) {
        this.detailDepositDao = detailDepositDao;
    }

    public BankAccountDao getBankAccountDao() {
        return bankAccountDao;
    }

    public void setBankAccountDao(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

//    ------------------------ Service

    public BankAccount openBankAccount(String accountNumber) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setBalance(0);
        bankAccount.setOpenTimestamp(new Date());

        return bankAccountDao.save(bankAccount);
    }

    public BankAccount getBankAccountByAccountNumber(String accountNumber) {

        BankAccount bankAccountReturn = bankAccountDao.getBankAccountByAccountNumber(accountNumber);

        return bankAccountReturn;
    }

    public BankAccount deposit(String accountNumber, long amount, String description) {

        DetailDeposit detailDeposit = new DetailDeposit(accountNumber, dateTimes, amount, description);
        mockList.add(detailDeposit);

//        detailDepositDao.save(detailDeposit);
        BankAccount bankAccount = bankAccountDao.getBankAccountByAccountNumber(accountNumber);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccount.addDescription(description);
        bankAccount.addDetailDeposit(detailDeposit);
        return bankAccountDao.save(bankAccount);
    }
}

package org.qsoft.tdd.kata2;

import ogr.qsoft.tdd.entity.BankAccount;
import ogr.qsoft.tdd.entity.DetailDeposit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 6/12/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */

public class TestBankAccount {

    List<DetailDeposit> mockList = mock(List.class);
    Date dateTimes = mock(Date.class);
    BankAccountDao mockAccountDao = mock(BankAccountDao.class);
    DetailDepositDao mockDetailDepositDao = mock(DetailDepositDao.class);
    BankAccountService bankAccountService = new BankAccountService();

    @Before
    public void setUp() {
        reset(mockAccountDao);
        reset(mockDetailDepositDao);
        bankAccountService.setBankAccountDao(mockAccountDao);
        bankAccountService.setDetailDepositDao(mockDetailDepositDao);
        bankAccountService.setMockList(mockList);
    }

    @Test
    public void openNewAccount() {
        bankAccountService.openBankAccount("1234567890");

        ArgumentCaptor<BankAccount> savedAccountRecords = ArgumentCaptor.forClass(BankAccount.class);
        verify(mockAccountDao).save(savedAccountRecords.capture());

        assertEquals(savedAccountRecords.getValue().getBalance(), 0.0, 0.01);
        assertEquals(savedAccountRecords.getValue().getAccountNumber(),"1234567890");
    }

    @Test
    public void testGetAccountByAccountNumber() {
        String strParam = "123";
        when(mockAccountDao.getBankAccountByAccountNumber(strParam)).thenReturn(new BankAccount(strParam, 0, new Date()));

        BankAccount bankAccount = bankAccountService.getBankAccountByAccountNumber(strParam);
        assertNotNull(bankAccount);

        assertEquals(bankAccount.getAccountNumber(),strParam);
    }

    @Test
    public void testAccountDeposit() {
        String strParam = "123";
        when(mockAccountDao.getBankAccountByAccountNumber(strParam)).thenReturn(new BankAccount(strParam, 0, new Date()));

        bankAccountService.deposit(strParam,100,"Deposit money");

        ArgumentCaptor<BankAccount> savedAccountRecords = ArgumentCaptor.forClass(BankAccount.class);
        verify(mockAccountDao).getBankAccountByAccountNumber(strParam);
        verify(mockAccountDao).save(savedAccountRecords.capture());

        assertEquals(savedAccountRecords.getValue().getBalance(), 100, 0.01);
        assertEquals(savedAccountRecords.getValue().getAccountNumber(),strParam);
    }

    @Test
    public void testAddLogDetailDeposit(){
        String strParam = "123";
        when(mockAccountDao.getBankAccountByAccountNumber(strParam)).thenReturn(new BankAccount(strParam, 0, new Date()));
        when(dateTimes.getTime()).thenReturn(1000L);



//        bankAccountService.deposit(strParam,100,"Deposit money");
////        dateTimes1.setYear(2013);
////        dateTimes1.setMonth(4);
////        dateTimes1.setDate(1);
////        dateTimes1.setHours(10);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println("XX:" +  df.format(dateTimes1));

    }
}

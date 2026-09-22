package com.saleh.smsaccounting;
public class Transaction{
 public long id,time;public String type,party,reference,body,source;public double amount,smsBalance,calculatedBalance;
 public Transaction(long id,String type,String party,String reference,String body,double amount,double smsBalance,long time){this(id,type,party,reference,body,amount,smsBalance,time,"SMS");}
 public Transaction(long id,String type,String party,String reference,String body,double amount,double smsBalance,long time,String source){this.id=id;this.type=type;this.party=party;this.reference=reference;this.body=body;this.amount=amount;this.smsBalance=smsBalance;this.time=time;this.source=source;}
}

package com.saleh.smsaccounting;
public class Transaction {
 public long id; public String type, party, reference, body; public double amount, smsBalance, calculatedBalance; public long time;
 public Transaction(long id,String type,String party,String reference,String body,double amount,double smsBalance,long time){this.id=id;this.type=type;this.party=party;this.reference=reference;this.body=body;this.amount=amount;this.smsBalance=smsBalance;this.time=time;}
}

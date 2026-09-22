package com.saleh.smsaccounting;
import android.content.*; import android.provider.Telephony;
public class SmsReceiver extends BroadcastReceiver{
 public void onReceive(Context c,Intent i){if(!Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(i.getAction()))return;for(android.telephony.SmsMessage m:Telephony.Sms.Intents.getMessagesFromIntent(i)){String body=m.getMessageBody();SmsAnalyzer.Result r=SmsAnalyzer.analyze(body);if(!r.type.equals("unknown")&&r.amount>0)new Db(c).add(new Transaction(0,r.type,r.party,r.reference,body,r.amount,r.smsBalance,m.getTimestampMillis()));}}
}

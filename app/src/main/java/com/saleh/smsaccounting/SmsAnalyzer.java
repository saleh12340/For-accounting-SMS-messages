package com.saleh.smsaccounting;
import java.util.regex.*;
public class SmsAnalyzer {
 public static class Result { String type="unknown", party="", reference=""; double amount=0, smsBalance=0; }
 static String digits(String s){ return s.replace('٠','0').replace('١','1').replace('٢','2').replace('٣','3').replace('٤','4').replace('٥','5').replace('٦','6').replace('٧','7').replace('٨','8').replace('٩','9').replace('۰','0').replace('۱','1').replace('۲','2').replace('۳','3').replace('۴','4').replace('۵','5').replace('۶','6').replace('۷','7').replace('۸','8').replace('۹','9'); }
 static double num(String s){try{return Double.parseDouble(digits(s).replace(",","").replace("٬","").trim());}catch(Exception e){return 0;}}
 public static Result analyze(String raw){
   Result r=new Result(); String s=digits(raw);
   if(s.matches("(?s).*خصم.*")||s.matches("(?s).*(سحب|دفع|تحويل صادر|إرسال).*")) r.type="debit";
   else if(s.matches("(?s).*(إيداع|استلام|تحويل وارد|وصلتك|استلمت).*")) r.type="credit";
   Matcher b=Pattern.compile("رصيد(?:كم|ك)\\s*[:：]?\\s*([0-9.,]+)").matcher(s); if(b.find()) r.smsBalance=num(b.group(1));
   Matcher a=Pattern.compile("(?:(?:خصم|إيداع|إضافة|تحويل)\\s*)([0-9.,]+)").matcher(s); if(a.find()) r.amount=num(a.group(1));
   if(r.amount==0){ Matcher x=Pattern.compile("([0-9][0-9,]*(?:\\.[0-9]+)?)").matcher(s); while(x.find()){double n=num(x.group(1)); if(Math.abs(n-r.smsBalance)>0.000001){r.amount=n;break;}}}
   Matcher ref=Pattern.compile("/([0-9]{6,})/").matcher(s); if(ref.find()) r.reference=ref.group(1);
   if(r.type.equals("debit")||r.type.equals("credit")) {
      String[] p=s.split("/"); if(p.length>1) r.party=p[1].trim();
   }
   return r;
 }
}

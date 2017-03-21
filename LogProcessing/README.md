1)
Java version Java 8 (jdk1.8.0_51)

2)
Log file directory to be configured in LogInsights main() method

2.1)  Java project to be compiled with directory modification and LogInsights with 2 parameters
2.2) ProductNoOfItem.java is not in use. Earlier added for multilevel reduce.


3)
Log file line : Sample
/products/api/v1/A0000000002/moto_00003/1

Read as:
Customer id = A0000000002
Product name = moto_00003
Number of Item = 1

3.1) Log files to be added as .txt file.

4) FYI I have used following command with immediate Ctrl C to generate files in GB
yes /products/api/v1/A0000000002/moto_00002/1 >>Log1.txt 


5) LogInsights program takes 2 input parameters

Example:1 3 denotes -> 1 Top customer and 3 Top product based on the number of items


6)Limitation / Issues of this program:
1) Date related business case has not been added; only first 2 business cases have been considered
2) I have used Java stream processing to handle the large file. This need batch wise map and reduce 
I have implemented with only one level of map and reduce. Currently it handles 500 MB within 15-20 seconds. 
For large files > 1GB I am getting memory issues. I could not add Batch processing, multilevel map reduce 
with optimal object / stream usage currently. There are approaches to handle as byte to process. I have not tried.


7) Testing:
Manual testing; Junit can be used for test automation

8) Eclipse IDE Console output for 5 500 MB files (Time in ms)

Getting Top buying 1 Customers::::Getting Top selling 3 Products
startTime1490100853298
File to Read/Users/arnavane/Documents/LogProcessingData/Log1 copy 2.txt
Product Processing Time20883
Customer Processing Time20550
Top 3 products {moto_00001=17196362, moto_00002=6520319, moto_00005=3552513}
Top 1 customers {A0000000002=10675186}
startTime1490100894733
File to Read/Users/arnavane/Documents/LogProcessingData/Log1 copy 3.txt
Product Processing Time20285
Customer Processing Time20114
Top 3 products {moto_00001=17196362, moto_00002=6520319, moto_00005=3552513}
Top 1 customers {A0000000002=10675186}
startTime1490100935133
File to Read/Users/arnavane/Documents/LogProcessingData/Log1 copy 4.txt
Product Processing Time18765
Customer Processing Time18353
Top 3 products {moto_00001=17196362, moto_00002=6520319, moto_00005=3552513}
Top 1 customers {A0000000002=10675186}
startTime1490100972252
File to Read/Users/arnavane/Documents/LogProcessingData/Log1 copy.txt
Product Processing Time20423
Customer Processing Time18392
Top 3 products {moto_00001=17196362, moto_00002=6520319, moto_00005=3552513}
Top 1 customers {A0000000002=10675186}
startTime1490101011068
File to Read/Users/arnavane/Documents/LogProcessingData/Log1.txt
Product Processing Time17714
Customer Processing Time18697
Top 3 products {moto_00001=17196362, moto_00002=6520319, moto_00005=3552513}
Top 1 customers {A0000000002=10675186}


9) I have committed the code in my git repository- https://github.com/aruneethan/try.git

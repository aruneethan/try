package javasrc;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogInsights {

	public static void main(String[] args) {

		try {
			// To read Top N customer and Top M selling products
			// Default value is n=1 m=1
			int nCustomer = 1;
			int mProducts = 1;
			if (null != args[0] && null != args[1]) {
				nCustomer = Integer.parseInt(args[0]);
				mProducts = Integer.parseInt(args[1]);
			}

			System.out.println("Getting Top buying " + nCustomer + " Customers::::" + "Getting Top selling " + mProducts
					+ " Products");

			// To read the Log line to make it as objects
			// List<String> topNCustomers = new ArrayList<>();
			// List<String> topMProducts = new ArrayList<>();

			// To read .txt files in the given directory
			DirectoryStream<Path> stream = Files.newDirectoryStream(
					Paths.get("/Users/arnavane/Documents/LogProcessingData/"),
					path -> path.toString().endsWith(".txt"));

			
			// For better scalability we can create mapped object to enable multi level reducer
			//Stream<Map.Entry<String, Integer>> productNoOfItemObj = null;
			//Currently Large file is having memory impact
			
			

			// To parse the directory stream
			for (Path path : stream) {
				Stream<String> productLines = null;
				Stream<String> customerLines = null;
				// To get product line stream
				productLines = Files.lines(path);
				customerLines = Files.lines(path);

				long startTime = System.currentTimeMillis();
				System.out.println("startTime" + startTime);
				System.out.println("File to Read" + path);
				Map<String, Integer> productInsight = new LinkedHashMap<>();
				Map<String, Integer> customerInsight = new LinkedHashMap<>();

				// To process the line stream to get insights
				productLines.map(line -> line.split("/"))
						.map(arr -> new ProductCheckout(arr[4], arr[5], Integer.parseInt(arr[6])))
						.collect(Collectors.toList()).parallelStream().parallel()
						.collect(Collectors.groupingBy(ProductCheckout::getProductName,
								Collectors.summingInt(ProductCheckout::getNumberOfItem)))
						.entrySet().stream().sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
						.limit(mProducts).forEachOrdered(x -> productInsight.put(x.getKey(), x.getValue()));

				
				long productProcessingTime = System.currentTimeMillis() ;
				long processingTime = System.currentTimeMillis() - startTime;
				
				System.out.println("Product Processing Time" + processingTime);
				
				// To process the line stream to get insights
				customerLines.map(line -> line.split("/"))
						.map(arr -> new ProductCheckout(arr[4], arr[5], Integer.parseInt(arr[6])))
						.collect(Collectors.toList()).parallelStream().parallel()
						.collect(Collectors.groupingBy(ProductCheckout::getCustomerId,
								Collectors.summingInt(ProductCheckout::getNumberOfItem)))
						.entrySet().stream().sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
						.limit(nCustomer).forEachOrdered(x -> customerInsight.put(x.getKey(), x.getValue()));

				long processingCustomerTime = System.currentTimeMillis() - productProcessingTime;
				
				System.out.println("Customer Processing Time" + processingCustomerTime);
				
				System.out.println("Top " + mProducts + " products " + productInsight);
				System.out.println("Top " + nCustomer + " customers " + customerInsight);

				customerLines.close();
				productLines.close();
			}
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

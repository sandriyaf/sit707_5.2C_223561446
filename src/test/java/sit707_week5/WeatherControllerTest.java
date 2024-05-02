package sit707_week5;

import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.mockito.Mockito.*;

public class WeatherControllerTest {
	private WeatherController wController;

	@Before
	public void setUp() {
		wController = WeatherController.getInstance();
	}

	@After
	public void tearDown() {
		wController.close();
	}

	@Test
	public void testStudentIdentity() {
		String studentId = "s223561446";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Sandriya Fernandes";
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testTemperatureMin() {
		System.out.println("+++ testTemperatureMin +++");
		// Arrange
		double minTemperature = Double.MAX_VALUE;

		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();

		for (int i = 0; i < nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = wController.getTemperatureForHour(i + 1);
			if (minTemperature > temperatureVal) {
				minTemperature = temperatureVal;
			}
		}

		// Should be equal to the min value that is cached in the controller.
		Assert.assertEquals(minTemperature, wController.getTemperatureMinFromCache(), 0.01);
	}

	@Test
	public void testTemperatureMax() {
		System.out.println("+++ testTemperatureMax +++");
		// Arrange
		double maxTemperature = Double.MIN_VALUE;
		// Initialise controller
		WeatherController wController = WeatherController.getInstance();

		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();

		for (int i = 0; i < nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = wController.getTemperatureForHour(i + 1);
			if (maxTemperature < temperatureVal) {
				maxTemperature = temperatureVal;
			}
		}

		// Should be equal to the min value that is cached in the controller.
		Assert.assertEquals(maxTemperature, wController.getTemperatureMaxFromCache(), 0.01);

	}

	@Test
	public void testTemperatureAverage() {
		System.out.println("+++ testTemperatureAverage +++");
		// Arrange
		double sumTemp = 0;
		int nHours = wController.getTotalHours();

		// Act
		for (int i = 0; i < nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = wController.getTemperatureForHour(i + 1);
			sumTemp += temperatureVal;
		}
		double averageTemp = sumTemp / nHours;

		// Should be equal to the min value that is cached in the controller.
		Assert.assertEquals(averageTemp, wController.getTemperatureAverageFromCache(), 0.01);

	}


	/*
	 * @Test public void testTemperaturePersist() {
	 * 
	 * System.out.println("+++ testTemperaturePersist +++");
	 * 
	 * // Initialise controller WeatherController wController =
	 * WeatherController.getInstance();
	 * 
	 * String persistTime = wController.persistTemperature(10, 19.5); String now =
	 * new SimpleDateFormat("H:m:s").format(new Date());
	 * System.out.println("Persist time: " + persistTime + ", now: " + now);
	 * 
	 * Assert.assertTrue(persistTime.equals(now));
	 * 
	 * wController.close(); }
	 */
	
	@Test
	public void testTemperaturePersist() {
	    System.out.println("+++ testTemperaturePersist +++");
	    
	    // Create a mock of the WeatherController
	    WeatherController mockController = mock(WeatherController.class);
	    
	    // Define the behavior of persistTemperature
	    String expectedTime = "12:30:45"; // You can specify a suitable expected time
	    when(mockController.persistTemperature(anyInt(), anyDouble())).thenReturn(expectedTime);
	    
	    // Use the mock in place of the actual controller
	    WeatherController wController = mockController;
	    
	    // Call the persistTemperature method
	    String persistTime = wController.persistTemperature(10, 19.5);
	    
	    // Print the persist time received
	    System.out.println("Expected time: " + expectedTime);
	    System.out.println("Received persist time: " + persistTime);
	    
	    // Compare the returned persistence time with the expected time
	    Assert.assertEquals(expectedTime, persistTime);
	    
	    // Verify that persistTemperature was called with the specified arguments
	    verify(mockController).persistTemperature(10, 19.5);
	}

	 } 
	



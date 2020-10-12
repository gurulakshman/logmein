package com.logmein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logmein.entity.Item;

/**
 * This class is Spring BOOT test  to test all the rest APIs.
 * @author Guru
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShoppingTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	HttpHeaders headers = new HttpHeaders();
	ObjectMapper mapper = new ObjectMapper();
	String url ;
	/**
	 * This method will test adding of new item.
	 * @throws Exception
	 */
	@Test
	public void testAddItem() throws Exception {
		String url = "http://localhost:" + port + "/shopping";

		Item item= new Item("item1",3L);
		ResponseEntity<String> response = restTemplate.postForEntity(url, item, String.class);
		assertEquals( HttpStatus.CREATED, response.getStatusCode() );
		
	}
	/**
	 * This method will test get all items 
	 * @throws Exception
	 */
	@Test
	public void testGetAllItems() throws Exception {
		String url = "http://localhost:" + port + "/shopping";
		// Insert test data
		Item item= new Item("itemall",13L);
		ResponseEntity<String> response = restTemplate.postForEntity(url, item, String.class);
		
		ResponseEntity<Item[]> responseEntity = restTemplate.getForEntity(url, Item[].class);
		Item[] itemArray =responseEntity.getBody();
		assertThat(item).isEqualToIgnoringGivenFields(itemArray[1], "id");
	}
	/**
	 * This method will test get item by id.
	 * @throws Exception
	 */
	@Test
	public void testfindById() throws Exception {
		String url = "http://localhost:" + port + "/shopping"+"/2";
		ResponseEntity<Item> responseEntity = restTemplate.getForEntity(url, Item.class);
		Item item =responseEntity.getBody();
		Item testItem = new Item(2L,"itemall",13L);
		assertThat(testItem).isEqualToIgnoringGivenFields(item, "id");
		
	}
	/**
	 * This method will test updated item.
	 * @throws Exception
	 */
	@Test
	public void testUpdateItem() throws Exception {
		String url = "http://localhost:" + port + "/shopping"+"/1";
		
		Item item= new Item(1L,"itemUpdated",20L);
		HttpEntity<Item> entity = new HttpEntity<Item>(item, headers);
		ResponseEntity<Item> response = restTemplate.exchange(url,HttpMethod.PUT, entity, Item.class);
		Item responseItem = response.getBody();
		
		assertThat(responseItem).isEqualToComparingFieldByField(item);
	}
	/**
	 * This method will test delete item.
	 * @throws Exception
	 */
	@Test
	public void testDeleteItem() throws Exception {
		String url = "http://localhost:" + port + "/shopping/1";
		Item item= new Item(1L,"itemUpdated",20L);
		HttpEntity<Item> entity = new HttpEntity<Item>(item, headers);
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.DELETE, entity, String.class);
		assertEquals( HttpStatus.OK, response.getStatusCode() );
	}
}

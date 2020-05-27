package com.github.pdimitrov97.foliotracker;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.github.pdimitrov97.foliotracker.model.Folio;
import com.github.pdimitrov97.foliotracker.model.FolioTracker;
import com.github.pdimitrov97.foliotracker.model.Stock;

public class ModelTests {


	private Stock stock;
	private Folio folio;
	private FolioTracker folioTracker;

	@Before
	public void setup() {
		stock = new Stock("AUTO", "AutoTrader", 20, 3.5);
		folio = new Folio("Test Folio");
		folioTracker = new FolioTracker("Test FolioTracker");
	}

	@Test
	public void setTickerTest() {
		stock.setTicker("SET");
		assertEquals("SET", stock.getTicker());
	}

	@Test
	public void setStockNameTest() {
		stock.setStockName("Set");
		assertEquals("Set", stock.getStockName());
	}

	@Test
	public void setNumberOfSharesTest() {
		stock.setNumberOfShares(10);
		assertEquals(10, stock.getNumberOfShares());
	}

	@Test
	public void setPricePerShareTest() {
		stock.setPricePerShare(3.0);
		assertEquals(3.0, stock.getPricePerShare(), 0);
	}

	@Test
	public void setFolioNameTest() {
		folio.setFolioName("Test");
		assertEquals("Test", folio.getFolioName());
	}


	@Test (expected = NullPointerException.class)
	public void addNullStockTest() {
		assertFalse(folio.addStock(null));
	}

	@Test
	public void addStockTest() {
		assertTrue(folio.addStock(stock));
	}

	@Test
	public void addSameStockAgainTest() {
		int n = stock.getNumberOfShares();
		folio.addStock(stock);
		folio.addStock(stock);
		assertEquals(2*n, folio.getStock(stock.getTicker()).getNumberOfShares());
	}

	@Test
	public void getFolioTotalValueTest() {
		folio.addStock(stock);
		assertEquals(70.0, folio.getFolioTotalValue(), 0);
	}

	@Test
	public void deleteStockTest() {
		folio.addStock(stock);
		assertTrue(folio.deleteStock("AUTO"));
	}

	@Test
	public void deleteNotExistingStockTest() {
		assertFalse(folio.deleteStock("AUTO"));
	}
	
	@Test
	public void getFolioStocksTest() {
		assertEquals(folio.getFolioStocks(), folio.getFolioStocks());
	}

	@Test
	public void setFolioTrackerNameTest() {
		folioTracker.setFolioTrackerName("Set");
		assertEquals("Set", folioTracker.getFolioTrackerName());
	}

	@Test
	public void addFolioTest() {
		assertTrue(folioTracker.addFolio(folio));
	}

	@Test
	public void addFolioAgainTest() {
		folioTracker.addFolio(folio);
		assertFalse(folioTracker.addFolio(folio));
	}

	//TODO is !=null in our specification or should we be handling it
	@Test (expected = NullPointerException.class)
	public void addFolioNullTest() {
		assertFalse(folioTracker.addFolio(null));
	}

	@Test
	public void renameFolioTest() {
		folioTracker.addFolio(folio);
		folioTracker.renameFolio("Test Folio", "Set");
		assertEquals("Set", folio.getFolioName());
	}

	@Test
	public void deleteFolioTest() {
		folioTracker.addFolio(folio);
		assertTrue(folioTracker.deleteFolio("Test Folio"));
	}

	@Test
	public void deleteNotExistingFolioTest() {
		assertFalse(folioTracker.deleteFolio(" "));
	}

	@Test
	public void getNotExistingFolioTest() {
		assertNull(folioTracker.getFolio(""));
	}

	@Test
	public void getFolioTest() {
		folioTracker.addFolio(folio);
		assertEquals(folio, folioTracker.getFolio("Test Folio"));
	}

	@Test
	public void getFoliosTest() {
		folioTracker.addFolio(folio);
		assertTrue(folioTracker.getFolios().contains(folio));
	}
	
	@Test
	public void renameNotExistingFolioTest() {
		assertFalse(folioTracker.renameFolio("unknown", "MyFolio"));
	
	}
}

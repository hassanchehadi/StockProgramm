public class TradingBot {
    private static final String SYMBOL = "AAPL";

    private AlphaVantageAPI alphaVantageAPI;
    private TradingStrategy tradingStrategy;

    public TradingBot() {
        this.alphaVantageAPI = new AlphaVantageAPI();
        this.tradingStrategy = new TradingStrategy();
    }

    public void run() {
        try {
            String stockData = alphaVantageAPI.getStockData(SYMBOL);
            double currentPrice = tradingStrategy.extractLastClosingPrice(stockData);

            double targetBuyPrice = tradingStrategy.calculateTargetBuyPrice(currentPrice);
            double targetSellPrice = tradingStrategy.calculateTargetSellPrice(currentPrice);

            if (tradingStrategy.shouldBuy(currentPrice, targetBuyPrice)) {
                System.out.println("Kaufauftrag für " + SYMBOL + " zum Preis von " + currentPrice);
                // Führe den Kaufauftrag aus
                tradingStrategy.entryPrice = currentPrice; // Speichere den Einstiegspreis
            } else if (tradingStrategy.shouldSell(currentPrice, targetSellPrice)) {
                System.out.println("Verkaufsauftrag für " + SYMBOL + " zum Preis von " + currentPrice);
                // Führe den Verkaufsauftrag aus
                tradingStrategy.entryPrice = 0.0; // Zurücksetzen des Einstiegspreises
            } else {
                System.out.println("Keine Handlungsanweisungen für " + SYMBOL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

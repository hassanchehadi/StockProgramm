public class TradingBot {
    private static final String SYMBOL = "AAPL";

    private ConnectorToAPI connectorToAPI;

    private TradingStrategy tradingStrategy;

    public TradingBot() {
        this.connectorToAPI = new ConnectorToAPI();
        this.tradingStrategy = new TradingStrategy();
    }

    public void run() {
        try {
            String stockData = connectorToAPI.getStockData(SYMBOL);
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

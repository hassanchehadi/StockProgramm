import net.sf.json.JSONObject;

public class TradingStrategy {
    private double stopLossPercent = 0.05; // Beispiel: 5% Stoploss-Schwelle
    public double entryPrice = 0.0; // Speichert den Einstiegspreis
    private double highestPrice = 0.0;
    private double trailingStopLossPercent = 0.03;

    public double extractLastClosingPrice(String jsonData) throws Exception {
   return 0;
    }

    public double calculateTargetBuyPrice(double currentPrice) {
        // Dynamische Anpassung des Zielkaufpreises
        return currentPrice * 0.95; // Beispiel: 5% unter dem aktuellen Preis
    }

    public double calculateTargetSellPrice(double currentPrice) {
        // Dynamische Anpassung des Zielverkaufspreises
        return currentPrice * 1.02; // Beispiel: 5% über dem aktuellen Preis
    }

    public boolean shouldBuy(double currentPrice, double targetBuyPrice) {
        // Implementiere deine Kaufstrategie hier
        return currentPrice <= targetBuyPrice && entryPrice == 0.0;
    }

    public boolean shouldSell(double currentPrice, double targetSellPrice) {
        // Überprüfe den Stopploss
        double stopLossPrice = entryPrice * (1 - stopLossPercent);
        if (currentPrice <= stopLossPrice && entryPrice != 0.0) {
            return true; // Verkaufen, wenn der Preis den Stopploss erreicht oder unterschreitet
        }

        // Überprüfe den Trailing Stoploss
        double trailingStopLossPrice = highestPrice * (1 - trailingStopLossPercent);
        if (currentPrice <= trailingStopLossPrice && entryPrice != 0.0) {
            return true; // Verkaufen, wenn der Preis den Trailing Stoploss erreicht oder unterschreitet
        }

        // Aktualisiere den bisher höchsten Preis
        if (currentPrice > highestPrice) {
            highestPrice = currentPrice;
        }

        // Implementiere deine eigene Verkaufsstrategie hier
        // ...

        return false; // Kein Verkaufssignal
    }
}

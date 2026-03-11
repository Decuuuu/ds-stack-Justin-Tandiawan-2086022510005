import java.time.LocalDate;

// 1. Custom Exception
class NoRidesLeftException extends Exception {
    public NoRidesLeftException(String message) {
        super(message);
    }
}

// 2. Base Superclass (Superclass contract + final pricing algorithm)
class Pass {
    protected String id;
    protected String ownerName;
    protected LocalDate createdOn;
    protected int renewalCount;

    // Protected constructor: Mencegah instansiasi langsung dari luar, tapi tidak pakai 'abstract'
    protected Pass(String id, String ownerName, LocalDate createdOn, int renewalCount) {
        this.id = id;
        this.ownerName = ownerName;
        this.createdOn = createdOn;
        this.renewalCount = renewalCount;
    }

    // Template Method: Algoritma harga sudah fix/final, tidak boleh diubah subclass
    public final long priceInCents() {
        long base = basePriceInCents();
        long subtotal = base - discountInCents(base) + penaltyInCents();
        long total = subtotal + taxInCents(subtotal);
        return Math.max(0, total); // Clamp to never negative
    }

    // Hook Methods: Default implementation (0), harus di-override subclass yang butuh
    protected long basePriceInCents() { return 0; }
    protected long discountInCents(long base) { return 0; }
    protected long penaltyInCents() { return 0; }
    protected long taxInCents(long subtotal) { return 0; }

    // Enforce implementation tanpa 'abstract'
    public Pass renew(LocalDate renewalDate) {
        throw new UnsupportedOperationException("renew not implemented for " + getClass().getSimpleName());
    }

    public void displayInfo() {
        System.out.println("[" + getClass().getSimpleName() + "] ID: " + id + ", Owner: " + ownerName + 
                           ", Price: " + priceInCents() + " cents");
    }
}

// 3. Intermediate Class: TimeBasedPass
class TimeBasedPass extends Pass {
    protected LocalDate startDate;
    protected LocalDate endDate;

    protected TimeBasedPass(String id, String ownerName, LocalDate createdOn, int renewalCount, LocalDate startDate, LocalDate endDate) {
        super(id, ownerName, createdOn, renewalCount);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public final boolean isActiveOn(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}

// 4. Concrete Leaf Class: WeeklyPass
class WeeklyPass extends TimeBasedPass {
    public WeeklyPass(String id, String ownerName, LocalDate start) {
        super(id, ownerName, LocalDate.now(), 0, start, start.plusDays(7));
    }

    @Override
    protected long basePriceInCents() { return 12000; }

    @Override
    public Pass renew(LocalDate renewalDate) {
        return new WeeklyPass(this.id + "-R" + (renewalCount + 1), this.ownerName, renewalDate);
    }
}

// 5. Intermediate Class: UsageBasedPass
class UsageBasedPass extends Pass {
    protected int ridesRemaining;

    protected UsageBasedPass(String id, String ownerName, LocalDate createdOn, int renewalCount, int ridesRemaining) {
        super(id, ownerName, createdOn, renewalCount);
        this.ridesRemaining = ridesRemaining;
    }

    public final void ride() throws NoRidesLeftException {
        if (ridesRemaining <= 0) throw new NoRidesLeftException("No rides left on pass!");
        ridesRemaining--;
        System.out.println("Ride consumed. Remaining: " + ridesRemaining);
    }
}

// 6. Concrete Leaf Class: TouristRidePack
class TouristRidePack extends UsageBasedPass {
    public TouristRidePack(String id, String ownerName) {
        super(id, ownerName, LocalDate.now(), 0, 10);
    }

    @Override
    protected long basePriceInCents() { return 18000; }

    @Override
    protected long penaltyInCents() {
        // Jika belum habis tapi sudah renew, kena penalty 5000
        return (ridesRemaining > 0) ? 5000 : 0;
    }

    @Override
    public Pass renew(LocalDate renewalDate) {
        TouristRidePack newPass = new TouristRidePack(this.id + "-R" + (renewalCount + 1), this.ownerName);
        newPass.renewalCount = this.renewalCount + 1;
        return newPass;
    }
}

// 7. Main Class (Sesuai nama file)
public class OOPW5 {
    public static void main(String[] args) {
        System.out.println("=== Demo Pass System ===");

        // 1. Create Concrete Passes
        WeeklyPass wp = new WeeklyPass("WP-001", "Justin", LocalDate.now());
        TouristRidePack tp = new TouristRidePack("TP-001", "Justin");

        wp.displayInfo();
        tp.displayInfo();

        System.out.println("\n=== Testing Usage & Exception ===");
        try {
            for (int i = 0; i < 11; i++) {
                tp.ride();
            }
        } catch (NoRidesLeftException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Testing Renewal ID Rule ===");
        Pass renewedTp = tp.renew(LocalDate.now());
        renewedTp.displayInfo();

        // Demonstrasi enforcement: Pass base tidak bisa renew
        // Pass p = new Pass("X", "X", LocalDate.now(), 0); // Error: Constructor protected
    }
}
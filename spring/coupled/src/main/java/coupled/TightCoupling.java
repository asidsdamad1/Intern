package coupled;

class Export1CSV {
    public void export(Object data) {
        System.out.println("Export " + data  +" with CSV format");
    }
}

// export chi dung cho csv
class Export1Data {
    private Export1CSV exportCSV = new coupled.Export1CSV();

    public Export1Data() {
    }

    public  void exportProcess(Object data) {
        this.exportCSV.export(data);
    }
}

public class TightCoupling {
    public static void main(String[] args) {
        Export1Data export1Data = new Export1Data();
        export1Data.exportProcess("Data");
    }
}

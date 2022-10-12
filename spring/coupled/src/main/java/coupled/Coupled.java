package coupled;

interface IExport {
    void export(Object data);
}

class ExportCSV implements IExport {

    @Override
    public void export(Object data) {
        System.out.println("Export " + data + " with CSV format");
    }
}

class ExportJSON implements IExport {

    @Override
    public void export(Object data) {
        System.out.println("Export " + data + " with JSON format");
    }
}

class ExportData {
    private IExport iExport;
//    private coupled.ExportCSV exportCSV = new coupled.ExportCSV();

    public ExportData() {
        iExport = new ExportCSV();
    }

    public ExportData(IExport iExport) {
        this.iExport = iExport;
    }

    public void exportProcess(Object data) {
        this.iExport.export(data);
    }
}

public class Coupled {

    public static void main(String[] args) {
//        coupled.ExportData exportData = new coupled.ExportData();
//        exportData.exportProcess("Data");

        IExport csvFormat = new ExportCSV();
        IExport jsonFormat = new ExportJSON();

        ExportData exportCSVData = new ExportData(csvFormat);
        ExportData exportJSONData = new ExportData(jsonFormat);

        exportCSVData.exportProcess("Data");
        exportJSONData.exportProcess("Data 1");
    }
}

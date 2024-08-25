package view;

/**
 * Class to communicate contract data.
 */
public class ContractData {
  
  private String startDate;
  private String endDate;

  public ContractData(String startDate, String endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }
  
}

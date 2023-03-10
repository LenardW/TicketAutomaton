package de.leuphana.cosa.printingsystem.structure;

public class Printer {
	private PrintFormat printFormat;

	public Printer(PrintFormat printFormat) {
		this.printFormat = printFormat;
	}

	public PrintFormat getPrintFormat() {
		return printFormat;
	}

	public boolean executePrintJob(PrintJob printJob) {
		//Output Printing
		System.out.println(printJob.getPrintable().getContent());
		printJob.changePrintJobState(PrintJobAction.PRINT);

		return true;
	}
}

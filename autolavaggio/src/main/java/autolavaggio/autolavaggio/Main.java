package autolavaggio.autolavaggio;

import autolavaggio.autolavaggio.model.*;
import autolavaggio.autolavaggio.controller.*;
import autolavaggio.autolavaggio.view.*;

public class Main {

	public static void main(String[] args) {
		Database db = new DBMysql("127.0.0.1", 3306, "autolavaggio", "root", "admin");
		DBAccessView view = new DBAccessView();
		DBAccessController controller = new DBAccessController(db, view);
		controller.initialize();
	}
}

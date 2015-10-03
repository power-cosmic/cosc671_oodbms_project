package edu.emich.power_cosmic.fog.queries;

import java.util.List;
import java.util.Scanner;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import edu.emich.power_cosmic.fog.schema.FogUser;

public class UserLister implements FogQuery {

	@Override
	public void runQuery(Scanner keyboard, ObjectContainer db) {
		List<FogUser> users = db.query(new Predicate<FogUser>() {

			@Override
			public boolean match(FogUser arg0) {
				return true;
			}
		});
		
		for (FogUser user: users) {
			System.out.println(user.getUsername() + ": "
					+ user.getPassword());
		}
	}

}

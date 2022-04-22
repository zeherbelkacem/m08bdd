package fr.fms.job;

import fr.frm.dao.Dao;
import fr.frm.dao.DaoFactory;
import fr.frm.entities.Utilisateur;

public class UserJobImpl implements UserJob {

	private Dao<Utilisateur> userDao = DaoFactory.getUserDao();
	@Override
	public int getUserId(String name) {
		// TODO Auto-generated method stub
		return userDao.getUserId(name);
	}

}

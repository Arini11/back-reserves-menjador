package team5.proyecto.reservesMenjador.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.ICategoryDAO;
import team5.proyecto.reservesMenjador.dao.IDishDAO;
import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;

@Service
public class DishServiceImpl implements IDishService {

	@Autowired
	private IDishDAO iDishDao;

	@Autowired
	private ICategoryDAO iCategoryDAO;

	@Override
	public List<Dish> getDishes() {
		return iDishDao.findAll();
	}
	
	public List<Dish> findByStatus(boolean status) {
		return iDishDao.findByStatus(status);
	}
	

	@Override
	public Dish findById(int id) {
		return iDishDao.findById(id).orElse(null);
	}

	@Override
	public Dish findByName(String name) {
		return iDishDao.findByName(name);
	}

	@Override
	public List<Dish> findByPopularity(int popularity) {
		return iDishDao.findByPopularity(popularity);
	}

	@Override
	public List<Dish> findByOrders(Order order) {
		return iDishDao.findByOrders(order);
	}

	@Override
	public List<Dish> findByCategories(Category category) {
		return iDishDao.findByCategories(category);
	}

	@Override
	public Dish saveDish(Dish dish) {
		// validar datos que entraran por body, que no se repita el nombre
		boolean exists = false;

		for (Dish iterateDish : getDishes()) {
			if (iterateDish.getName().equals(dish.getName())) {
				exists = true;
			}
		}
		if (!exists) {
			List<Category> newCategories = new ArrayList<Category>();
			for (Category c : dish.getCategories()) {
				Category cat = iCategoryDAO.findById(c.getId()).orElse(null);
				newCategories.add(cat);
			}
			dish.setCategories(newCategories);
			dish.setImage(null); // La imatge no es posa per aqu??
			return iDishDao.save(dish);

		}
		return null;
	}

	@Override
	public Dish updateDishImage(int id, byte[] imatge) {
		Dish d = iDishDao.findById(id).orElse(null);
		d.setImage(imatge);
		return iDishDao.save(d);
	}

	@Override
	public Dish deleteDish(int id) {
		Dish dish = findById(id);
		dish.setStatus(false);
		return iDishDao.save(dish);
	}

	@Override
	public Dish updateDish(Dish dish) {
		Dish dishU = findById(dish.getId());

		dishU.setName(dish.getName() == null ? dishU.getName() : dish.getName());
		dishU.setDescripcion(dish.getDescripcion() == null ? dishU.getDescripcion() : dish.getDescripcion());
		// dishU.setImage(dish.getImage() == null ? dishU.getImage() :
		// compressZLib(imatge));
		dishU.setPopularity(dish.getPopularity() == 0 ? dishU.getPopularity() : dish.getPopularity());
		dishU.setStatus(dish.isStatus() == false ? dishU.isStatus() : dish.isStatus());

		List<Category> newCategories = new ArrayList<Category>();
		for (Category c : dish.getCategories()) {
			Category cat = iCategoryDAO.findById(c.getId()).orElse(null);
			newCategories.add(cat);
		}
		dishU.setCategories(newCategories);

		return iDishDao.save(dishU);
	}

}

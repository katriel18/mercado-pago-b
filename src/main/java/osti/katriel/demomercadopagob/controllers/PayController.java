package osti.katriel.demomercadopagob.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;

@RestController
@RequestMapping("/pay")
public class PayController {

	/*
	 * @Autowired UsuarioService usuarioService;
	 */

	/*
	 * @GetMapping() public ArrayList<UsuarioModel> obtenerUsuarios(){ return
	 * "Hola Katriel"; }
	 */
	@GetMapping()
	public String obtener() {
		return "Hola Katriel";
	}

	@PostMapping()
	public String guardarUsuario(@RequestBody Item item) {

		Preference newPreference = null;

		// Agrega credenciales
		try {
			// MercadoPago.SDK.setAccessToken("TEST-3083360996217582-021404-d5340694e381f3c7847796d2edd0b6c0-434380225");
			MercadoPago.SDK
					.setAccessToken("APP_USR-8208253118659647-112521-dd670f3fd6aa9147df51117701a2082e-677408439");

		} catch (MPConfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en el Access token!");
		}

		// Crea un objeto de preferencia
		Preference preference = new Preference();

		// Crea un item en la preferencia
		/*
		 * Item item1 = new Item();
		 * item.setTitle("Producto de Katriel 1").setQuantity(1).setUnitPrice((float)
		 * 80.00);
		 * 
		 * Item item2 = new Item();
		 * item2.setTitle("Producto de Katriel 2").setQuantity(1).setUnitPrice((float)
		 * 20.00);
		 */

		Item item0 = new Item();
		item0.setTitle(item.getTitle()).setQuantity(item.getQuantity()).setUnitPrice((float) item.getUnitPrice());
		preference.appendItem(item0);
		// preference.appendItem(item).appendItem(item2).appendItem(item3);

		try {
			newPreference = preference.save();
			System.out.println("Codigo de pago: " + newPreference.getId());
			System.out.println("Link de pago(InitPoint): " + newPreference.getInitPoint());
			System.out.println("Link de pago(sandbox_init_point): " + newPreference.getSandboxInitPoint());
			System.out.println("Producto(s):");
			for (Item i : newPreference.getItems()) {

				System.out.println(i.getTitle() + ", " + i.getQuantity() + ", " + i.getUnitPrice());
			}
		} catch (MPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error en  Guardado de la referencia!");
		}

		return newPreference.getInitPoint();

		// return this.usuarioService.guardarUsuario(usuario);
	}
	/*
	 * @GetMapping( path = "/{id}") public Optional<UsuarioModel>
	 * obtenerUsuarioPorId(@PathVariable("id") Long id) { return
	 * this.usuarioService.obtenerPorId(id); }
	 * 
	 * @GetMapping("/query") public ArrayList<UsuarioModel>
	 * obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
	 * return this.usuarioService.obtenerPorPrioridad(prioridad); }
	 * 
	 * @DeleteMapping( path = "/{id}") public String
	 * eliminarPorId(@PathVariable("id") Long id){ boolean ok =
	 * this.usuarioService.eliminarUsuario(id); if (ok){ return
	 * "Se eliminó el usuario con id " + id; }else{ return
	 * "No pudo eliminar el usuario con id" + id; } }
	 */
}

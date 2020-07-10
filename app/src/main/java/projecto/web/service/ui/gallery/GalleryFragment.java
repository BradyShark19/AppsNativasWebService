/*Paquete*/
package projecto.web.service.ui.gallery;

/*Librerias*/
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import projecto.web.service.R;
import projecto.web.service.VolleySingleton;

/*Clase Principal*/
public class GalleryFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener
{ /*Variables*/
   EditText CampoDocumento, CampoNombre, CampoProfesion;
   Button BtnRegistrar;
   ProgressDialog Progreso;

   RequestQueue request;
   JsonObjectRequest jsonObjectRequest;

        /*Metodos*/

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        CampoDocumento = (EditText) root.findViewById(R.id.CampoDocumento);
        CampoNombre = (EditText) root.findViewById(R.id.CampoNombre);
        CampoProfesion = (EditText) root.findViewById(R.id.CampoProfesion);
        BtnRegistrar = (Button) root.findViewById(R.id.BtnRegistrar);

        request = Volley.newRequestQueue(getContext());
       //VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(@Nullable String s) {textView.setText(s); }
        });

        BtnRegistrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CargarWebService();
            }
        });

        return root;
    }

    private void CargarWebService()
    {
        Progreso = new ProgressDialog(getContext());
        Progreso.setMessage("Cargando...");
        Progreso.show();

        String Ip = getString(R.string.Ip);

        String Url= Ip+"ProjectoWebService/WSJSONRegistro.php?documento="+CampoDocumento.getText().toString()+
                "&nombre="+CampoNombre.getText().toString()+"&profesion="+CampoProfesion.getText().toString();

        Url.replace("","%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,Url,null,this, this);
        request.add(jsonObjectRequest);
        // VolleySingleton.getInstanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public void onResponse(JSONObject response)
    {
        Toast.makeText(getContext(),"Se ha registrado correctamente",Toast.LENGTH_SHORT).show();
        Progreso.hide();
        CampoDocumento.setText("");
        CampoNombre.setText("");
        CampoProfesion.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        Progreso.hide();
        Toast.makeText(getContext(),"No se pudo registrar, debido a: "+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }


}
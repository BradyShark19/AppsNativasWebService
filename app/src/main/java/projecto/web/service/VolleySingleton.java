/*Paquete*/
package projecto.web.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/*Clase Principal */
public class VolleySingleton
{ /*Variables*/
    private static VolleySingleton InstanciaVolley;
    private RequestQueue Request;
    private static Context Contexto;

  /*Metodos*/

    private VolleySingleton(Context context)
    {
        Contexto = context;
        Request = getRequestQueue();
    }

    private RequestQueue getRequestQueue()
    {
        if (Request == null)
        {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            Request = Volley.newRequestQueue(Contexto.getApplicationContext());
        }
        return Request;
    }

    public <T> void addToRequestQueue(com.android.volley.Request<T> request)
    {
        getRequestQueue().add(request);
    }



    public static synchronized VolleySingleton getInstanciaVolley(Context context)
    {
        if (InstanciaVolley == null) {
            InstanciaVolley = new VolleySingleton(context);
        }

        return InstanciaVolley;
    }
}

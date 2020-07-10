package projecto.web.service.ui.consultalistausers;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projecto.web.service.R;

public class ConsultaListaUserFragment extends Fragment {

    private ConsultaListaUserViewModel mViewModel;

    public static ConsultaListaUserFragment newInstance() {
        return new ConsultaListaUserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.consulta_lista_user_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConsultaListaUserViewModel.class);
        // TODO: Use the ViewModel
    }

}
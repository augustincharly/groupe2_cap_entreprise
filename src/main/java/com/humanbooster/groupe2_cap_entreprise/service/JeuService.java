package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.configuration.EnvironmentVariable;
import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.JeuFormWrapper;
import com.humanbooster.groupe2_cap_entreprise.repository.JeuRepository;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;

@Component
public class JeuService implements IJeuService {

	@Autowired
	private JeuRepository jeuRepository;
	
	@Autowired
	private IEditeurService editeurService;
	
	@Autowired
	private IGenreService genreService;
	
	@Autowired
	private IClassificationService classificationService;
	
	@Autowired
	private IModeleEconomiqueService modeleEconomiqueService;
	
	@Autowired
	private IPlateformeService plateformeService;

	public List<JeuDTO> getJeux() {
		List<JeuDTO> jeuDTOs = new ArrayList<JeuDTO>();

		jeuRepository.findAll().forEach(jeu -> {
			JeuDTO jeuDTO = TransformerFactory.getJeuTransformer().transform(jeu);
			jeuDTOs.add(jeuDTO);
		});

		return jeuDTOs;
	}

	@Override
	public void save(Jeu jeu) {
		jeuRepository.save(jeu);
	}

	public List<Jeu> getAllJeux() {
		try {
			return jeuRepository.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Jeu getJeuByID(Long jeu_id) {
		Jeu jeu = jeuRepository.findById(jeu_id).get();
		return jeu;
	}

	@Override
	public JeuDTO getJeuDTO(Long id) {
		Jeu jeuEntity = jeuRepository.findById(id).get();

		JeuDTO jeuDTO = TransformerFactory.getJeuTransformer().transform(jeuEntity);

		return jeuDTO;
	}

	@Override
	public void delete(Long id) {
		jeuRepository.deleteById(id);
	}

	@Override
	public void save(JeuFormWrapper createjeu) {
		Jeu jeu = new Jeu();
		jeu.setNom(createjeu.getJeu_nom());
		jeu.setDateDeSortie(createjeu.getDateDeSortie());
		jeu.setDescription(createjeu.getJeu_description());
		jeu.setEditeur(editeurService.findEditeurById(createjeu.getEditeur_id()));
		jeu.setGenre(genreService.findGenreById(createjeu.getEditeur_id()));
		jeu.setClassification(classificationService.findClassificationById(createjeu.getClassification_id()));
		jeu.setModeleEconomique(modeleEconomiqueService.findModeleEconomiqueById(createjeu.getModeleEconomique_id()));
		for(Long plateformeId:createjeu.getPlateformes_id()) {
			jeu.getPlateformes().add(plateformeService.findPlateformeById(plateformeId));
		}
		jeuRepository.save(jeu);
		
	}

	@Override
	public List<JeuDTO> getJeuDTOsWithPagination(Pageable pagination) {
		List<Jeu> jeux =  jeuRepository.findAll(pagination).getContent();
		List<JeuDTO> jeuDTOs = new ArrayList<>();
		for(Jeu jeuToAdd : jeux) {
			jeuDTOs.add(TransformerFactory.getJeuTransformer().transform(jeuToAdd));
		}
				
		return jeuDTOs;
	}

	@Override
	public Page<Jeu> getJeuPageDTOsWithPagination(Pageable pagination) {
		Page<Jeu> jeuDTOs =  jeuRepository.findAll(pagination);
		return jeuDTOs;
	}

	@Override
	public List<JeuDTO> getAllJeuDTOs() {
		List<Jeu> jeux =  jeuRepository.findAll();
		List<JeuDTO> jeuDTOs = new ArrayList<>();
		for(Jeu jeuToAdd : jeux) {
			jeuDTOs.add(TransformerFactory.getJeuTransformer().transform(jeuToAdd));
		}
				
		return jeuDTOs;
	}

	@Override
	public Page<Jeu> getAllPageJeuxSorted(Integer pageNum, String sortField, String sortDir) {
		int pageSize = EnvironmentVariable.ITEMS_PER_PAGE;
		Pageable pageable = PageRequest.of(pageNum, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
		return jeuRepository.findAll(pageable);
	}
	
	

}

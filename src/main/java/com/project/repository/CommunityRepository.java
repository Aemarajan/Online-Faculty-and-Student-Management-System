package com.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.model.Community;

public interface CommunityRepository extends JpaRepository<Community, Integer>{

	List<Community> findByAcronym(String acronym);
	
	List<Community> findByName(String name);

	@Transactional
	@Modifying
	@Query(value="UPDATE community_master c SET c.name=:name, c.acronym=:acc, c.inn=:inn WHERE c.id=:id",nativeQuery=true)
	void update(@Param("name")String name,@Param("acc") String acronym,@Param("inn") int inn,@Param("id") int id);

	@Transactional
	@Modifying
	@Query(value="SELECT * FROM community_master c WHERE c.name=:name AND c.acronym=:acc",nativeQuery=true)
	List<Community> findByNames(@Param("name")String name,@Param("acc") String acronym);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE community_master c SET c.inn=:inn WHERE c.id=:id",nativeQuery=true)
	void updateToInn(@Param("inn") int inn,@Param("id") int id);

}

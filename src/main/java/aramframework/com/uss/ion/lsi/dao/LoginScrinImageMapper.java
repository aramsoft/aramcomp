package aramframework.com.uss.ion.lsi.dao;

import java.util.List;

import aramframework.com.cmm.domain.FileVO;
import aramframework.com.uss.ion.lsi.domain.LoginScrinImageVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 개요
 * - 로그인화면이미지에 대한 DAO 클래스를 정의한다.
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 */
@Mapper
public interface LoginScrinImageMapper {

	/**
	 * 로그인화면이미지정보를 관리하기 위해 등록된 로그인화면이미지 목록을 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public List<EgovMap> selectLoginScrinImageList(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지목록 총 갯수를 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public int selectLoginScrinImageListCnt(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 등록된 로그인화면이미지의 상세정보를 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public LoginScrinImageVO selectLoginScrinImage(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지정보를 신규로 등록한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public void insertLoginScrinImage(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 기 등록된 로그인화면이미지정보를 수정한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public void updateLoginScrinImage(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 기 등록된 로그인화면이미지정보를 삭제한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public void deleteLoginScrinImage(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 기 등록된 로그인화면이미지정보의 이미지파일을 삭제하기 위해 파일정보를 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public FileVO selectLoginScrinImageFile(LoginScrinImageVO loginScrinImageVO);

	/**
	 * 로그인화면이미지가 특정화면에 반영된 결과를 조회한다.
	 * 
	 * @param loginScrinImageVO
	 */
	public List<EgovMap> selectLoginScrinImageResult(LoginScrinImageVO loginScrinImageVO);
	
}

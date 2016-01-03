package aramframework.com.uss.ion.tir.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;
import aramframework.com.uss.ion.tir.service.TwitterTrnsmitService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 트위터수신을 처리하는 ServiceImpl Class 구현
 * 
 * @author 아람컴포넌트 조헌철
 * @since 2014.11.11
 * @version 1.0
 * @see
 *
 * <pre>
 * 
 * << 개정이력(Modification Information) >>
 *   
 *   수정일            수정자          수정내용
 *   -------     ------   ---------------------------
 *   2014.11.11  조헌철         최초 생성
 * 
 * </pre>
 */

@Service("twitterTrnsmitService")
public class TwitterTrnsmitServiceImpl extends EgovAbstractServiceImpl implements TwitterTrnsmitService {

	/* 트위터 DAO */
	@Resource(name = "twitterMapper")
	private TwitterMapper twitterMapper;	

	/**
	 * 트위터 계정을 건수를 조회 한다.
	 * 
	 * @param param
	 *            -조회할 정보가 담긴 객체
	 * @return Map - 조회 정보가 담긴 Map
	 * @throws Exception
	 */
	public EgovMap selectTwitterAccount(String usid) throws Exception {
		return (EgovMap) twitterMapper.selectTwitterAccount(usid);
	}

	/**
	 * 트위터 계정을 건수를 조회 한다.
	 * 
	 * @param param
	 *            -조회할 정보가 담긴 객체
	 * @return int - 조회 정보가 담긴 Integer
	 * @throws Exception
	 */
	public int selectTwitterAccountCheck(String usid) throws Exception {
		return twitterMapper.selectTwitterAccountCheck(usid);
	}

	/**
	 * 트위터 계정을 신규로 등록한다.
	 * 
	 * @param param
	 *            - 조회할 정보가 담긴 Map
	 */
	public void insertTwitterAccount(Map<String, String> param) throws Exception {
		twitterMapper.insertTwitterAccount(param);
	}

	/**
	 * 트위터 계정을 수정한다.
	 * 
	 * @param param
	 *            - 조회할 정보가 담긴 Map
	 */
	public void updateTwitterAccount(Map<String, String> param) throws Exception {
		twitterMapper.updateTwitterAccount(param);
	}

	/**
	 * 트위터 계정을 삭제한다.
	 * 
	 * @param param
	 *            - 조회할 정보가 담긴 Map
	 */
	public void deleteTwitterAccount(Map<String, String> param) throws Exception {
		twitterMapper.deleteTwitterAccount(param);
	}

	/**
	 * 트위터를 송신하다.
	 * 
	 * @param sTwitterId
	 *            -트위터 아이디
	 * @param sTwitterPw
	 *            -트위터 비밀번호
	 * @param sTwitterText
	 *            -트위터 등록 메세지
	 */
	public Status twitterTrnsmitRegist(Map<String, String> map, String sTwitterText) throws Exception {

		// 엑서스 토큰 키 설정
		AccessToken accessToken = new AccessToken((String) map.get("atoken"), (String) map.get("astoken"));

		// 트위터 객체선언
		Twitter twitter = new TwitterFactory().getOAuthAuthorizedInstance(
				(String) map.get("sCONSUMER_KEY"), (String) map.get("sCONSUMER_SECRET"), accessToken);

		// 트위터 글 게시
		return (Status) twitter.updateStatus(sTwitterText);

	}

}
